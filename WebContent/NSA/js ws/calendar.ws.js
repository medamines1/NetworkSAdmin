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

	if (data.exec=='true') {
		eval("w."+data.action+"(data.data)");
	}


}




var webs =	class {
	constructor(){
	 var s =new WebSocket("ws://"+document.location.host+"/ws/calendar");

	 s.sendaction = function (action){
		s.send(JSON.stringify({'action':action}));
	}
	
	s.onopen = function (){

	}



	s.onmessage =  function (event){

  		var d = event.data;
  		
  		var data = JSON.parse(d);
		processResponse(data);

  			

	 

  


  	}

  	s.get_my_dates = function (data){
  		var d = JSON.parse(data);
  		var keys = Object.keys(d);
		//$('#calendar').fullCalendar('destroy');
  		//$('#calendar').fullCalendar();
  		$('#calendar').fullCalendar('removeEvents'); 
  		for (var i =0 ; i< keys.length ; i++){
  			
  			$('#calendar').fullCalendar('renderEvent', JSON.parse(d[keys[i]]), true);

  		}

  	}






	s.update = function(){
		s.send(JSON.stringify({'action':'get_my_dates'}));
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