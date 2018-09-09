
 var webs =  class {

 	constructor(){

 		return connect();
 	}

 }


 var w = new webs();


 processResponse = function (data){
	 	data = JSON.parse(data);
		state = "success";
		if(data.response == true)
			if (data.err==true)
				state = "error";
			if (data.msgplt )
					d = data.msgplt;
			if (data.plt)
			swal({
	  			text: d,
	  			icon: state,
	  			button: "ok",
		});


}


function connect() {
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {

		
	stompClient.send("/app/calendar/getAll",{},{});

	stompClient.subscribe("/topic/calendar/getAll", function(data){
		get_my_dates(data.body);
		});
	
	
	stompClient.subscribe("/topic/calendar/new", function(data){
		processResponse(data.body);
		});
	
	
	stompClient.subscribe("/topic/calendar/delete", function(data){
		processResponse(data.body);
		});


	

	});

	return stompClient;
	}


get_my_dates = function (data){
		var d = JSON.parse(JSON.parse(data).result);
		var keys = Object.keys(d);
	//$('#calendar').fullCalendar('destroy');
		//$('#calendar').fullCalendar();
		$('#calendar').fullCalendar('removeEvents'); 
		for (var i =0 ; i< keys.length ; i++){

			$('#calendar').fullCalendar('renderEvent', JSON.parse(d[keys[i]]), true);

		}

	}


setInterval(function(){
	w.send("")
},5000)
 
 
