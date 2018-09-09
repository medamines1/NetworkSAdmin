
main_Data =$('#data-holder').data({
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
	console.log(elm.delegateTarget.id + " ---------------------------------------->");
	var target =elm.delegateTarget.id;
	var data = main_Data.data().machines[target];
	var plt = $("#tmplate_for_"+target.slice(0,target.length-1));
	
	if (data !=undefined){
	var keys = Object.keys(data);
	plt.empty();
	for(var i=0;i < keys.length ; i++){
		console.log(data[i]);
		plt.append(tmplate_for_dev(data[i],target.slice(0,target.length-1)));
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






function connect() {
var socket = new SockJS('/ws');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {




stompClient.subscribe("/topic/home/getNumers", function(data){
		update_number(data);
	});


stompClient.subscribe("/topic/home/getMachines", function(data){
	get_all_data_of_machines(data);
});






stompClient.subscribe("/topic/home/action/send", function(data){
	
});



stompClient.subscribe("/topic/home/action/sendbhv", function(data){
	var d =processResponse(data);
	console.log(data);
});




});

return stompClient;
}






function update_number(data){
	var updates = ["computers","others","phones","printers","donlines","msgs"];	
	data = JSON.parse(JSON.parse(data.body).result);
		for(var i=0;i<6;i++)
		{
			$("#val_of_"+updates[i]).html(data[updates[i]]);
		}
}


function get_all_data_of_machines(data){
	//main_Data.data().machines
	data = JSON.parse(JSON.parse(data.body).result);
	delete data['action'];
	var keys =Object.keys(data);
	var key,tmp;
	for (var i=0;i < keys.length;i++){
		key =keys[i];
		delete main_Data.data().machines[key];
		main_Data.data().machines[key] = JSON.parse(data[key]);
	}


}




var webs =  class {

	constructor(){

		return connect();
	}

}


var w = new webs();

setInterval(function(){

		w.send("/app/home/getNumbers",
    		{},{});

		w.send("/app/home/getMachines",
	    		{},{});
	
},5000)



send_msg = function(t){

    w.send("/home/action/sendmsg",{},JSON.stringify({
 	   host:$("#host_mach").val(),
 	   args:JSON.stringify({c_msg:$('#msg_body').val()}), 
    }));
    
  }

  send_act = function(t){
    
    w.send("/app/home/action/sendbhv",{},JSON.stringify({
  	   host:$("#host_mach").val(),
  	   args:JSON.stringify({
  		 "action":'/behaviour/lock'
  	   }), 
     }));
	
  }


    send_act2 = function(t){

	
        w.send("/app/home/action/sendbhv",{},JSON.stringify({
       	   host:$("#host_mach").val(),
       	   args:JSON.stringify({
       		 "action":'/behaviour/restart'
       	   }), 
          }));
  }
    
