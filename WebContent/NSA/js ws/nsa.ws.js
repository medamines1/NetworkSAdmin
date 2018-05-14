window.getCookie = function(name) {
  match = document.cookie.match(new RegExp(name + '=([^;]+)'));
  if (match) return match[1];
}




main_Data =$('#data-holder').data({
	'id':getCookie('user_id'),
	'machines':{
		"computers":{},
		"phones":{},
		"printers":{},
		"others":{},
	},
	'length_':{
		'msg':0,
		'devices':0,
		'callendar':0,
		'chart':0,
	}
});


$(".info-box").click(function(elm){
	console.log(elm.delegateTarget.id);
	var target =elm.delegateTarget.id;
	var data = main_Data.data().machines[target];
	var plt = $("#tmplate_for_"+target.slice(0,target.length-1));
	console.log(data);
	if (data !=undefined){
	var keys = Object.keys(data);
	plt.empty();
	for(var i=0;i < keys.length ; i++){
		plt.append(tmplate_for_dev(data[keys[i]],target.slice(0,target.length-1)));
	}
}
});



processResponse = function (data){
	if(data.resp == 'true'){
		if (data.error == 'true')
				d = data.error_msg;
		else 
				d = data.action;

		if (data.hiden=="false")
		swal({
  			text: d,
  			icon: data.state,
  			button: "ok",
	});
		}


	delete data['resp'];
	delete data['hiden'];
	delete data['error'];
	delete data['state'];
	return data;

}




var webs =	class {
	constructor(){
	 var s =new WebSocket("ws://"+document.location.host+"/ws/home");

	 s.sendaction = function (action){
		s.send("{'action':'" + action + "'}");
	}
	
	s.onopen = function (){
	s.sendaction("get_all_pack");
	s.sendaction("get_data");

	}


	s.get_all_pack = function (data){
		var updates = ["msgs","computers","donlines","others","phones","printers"];
		for(var i=0;i<6;i++)
		{
			$("#val_of_"+updates[i]).html(data[updates[i]]);
		}

	}


	s.get_data = function(data){
		//main_Data.data().machines
		delete data['action'];
		var keys =Object.keys(data);
		var key,tmp;
		for (var i=0;i < keys.length;i++){
			key =keys[i];
			delete main_Data.data().machines[key];
			main_Data.data().machines[key] = JSON.parse(data[key]);
		}


	}
	s.onmessage =  function (event){

  		var d = event.data;
  		var data = JSON.parse(d);
		data = processResponse(data);
  		try{
  			eval("s."+data.action+"(data)");

	  		}catch(error){
	  			console.log(error);
	  		}

  


  	}


	s.update = function(){
		//s.send("")	;
	}

 
	s.onclose = function (){
						swal({
  			text: "go to login page",
  			icon: "warning",
  			button: "go",
  			 dangerMode: true,
  			
	}).then((willGo) => {
			  if (willGo) {
		         location.href ='/';
			  } 

			});
		}

	
		return s;

	}

}


var w = new webs();

setInterval(function(){
	w.onopen();
},5000)