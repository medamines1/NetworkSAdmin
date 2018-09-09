window.getCookie = function(name) {
  match = document.cookie.match(new RegExp(name + '=([^;]+)'));
  if (match) return match[1];
}




main_Data =$('#data-holder').data({

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
					window.location = window.location.origin;
			  } 

			});
		}

	
		return s;

}



}




var w = new webs();

setInterval(function(){
	w.update();
},5000)