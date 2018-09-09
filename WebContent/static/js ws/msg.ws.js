window.getCookie = function(name) {
  match = document.cookie.match(new RegExp(name + '=([^;]+)'));
  if (match) return match[1];
}


main_Data =$('#data-holder').data({
	'id':getCookie('user_id'),
	'msg':{
		'Inbox':{},
		'Sent': {},
		'Drafts':{},
		'Junk':{},
		'Trash':{},
	},
	'length_msg':{
		'Inbox':0,
		'Sent':0,
		'Drafts':0,
		'Junk':0,
		'Trash':0,
	}
});




var update = function(item){
	item.update();
}


class pool extends Array {
    constructor(...args) { 
        super(...args); 
    }
}




processResponse = function (data){
	if(data.resp == 'true'){
		if (data.error == 'true')
				d = data.error_msg;
		else 
				d = data.action;

		swal({
  			text: d,
  			icon: data.state,
  			button: "ok",
	});
		}


}


set_length = function (){
	$('#length_msg_Inbox').html(main_Data.data().length_msg.Inbox);
	$('#length_msg_Sent').html(main_Data.data().length_msg.Sent);
	$('#length_msg_Drafts').html(main_Data.data().length_msg.Drafts);
	$('#length_msg_Junk').html(main_Data.data().length_msg.Junk);
	$('#length_msg_Trash').html(main_Data.data().length_msg.Trash);
}

plt_msges = function (data) {

	//
	delete data['action'];
	keys =Object.keys(data);
	for (i=0;i < keys.length;i++)
		{	
			key =keys[i];
			tmp = JSON.parse(data[key]);
			if ( main_Data.data().msg[tmp.placedin][key] == undefined ){
				if (tmp.placedin=='Sent' && tmp.sender==main_Data.data().id){
				main_Data.data().length_msg[tmp.placedin] ++;	
				main_Data.data().msg[tmp.placedin][key] = tmp;
				}
				else{
					main_Data.data().length_msg['Inbox'] ++;	
					main_Data.data().msg['Inbox'][key] = tmp;

				}

				set_length();
				if (tmp.placedin=='Inbox' && (tmp.sender != main_Data.data().id) && (tmp.seen != '0')){
				alertify.logPosition("bottom right");
    			alertify.success("you have new message from userid "+tmp.sender +" .");
    						}
			
						}
				}
		}


 plt_sfolder = function(type,color){
 	holder = $('#plt_folder');
 	holder.empty();
 	pl = container_tmp(type,color);
 	holder.append(pl);

 	plt = $("#plt_msg");
 	keys = Object.keys(main_Data.data().msg[type]);
 	for(i=0;i< main_Data.data().length_msg[type];i++){
		tmp = main_Data.data().msg[type][keys[i]];
		gkey =keys[i];
		console.log(gkey);
 		plt.append(msg_tmp(gkey,tmp.sender,tmp.subj,tmp.msg,tmp.timestamp,type));
 	}

 }

 compose = function(){
 	holder = $('#plt_folder');
 	holder.empty();
 	pl = send_msg_tmp()	;
 	holder.append(pl);
 }

 readmsg = function(where,id){
 	discard();
 	var tmp = main_Data.data().msg[where][id];
 	pl = container_read_tmp(tmp.msg,tmp['receiver'],tmp.subj,'primary');
 	holder = $('#plt_folder');
 	holder.append(pl);

 }

 discard = function (){
 	holder = $('#plt_folder');
 	holder.empty();
 }

 sendmsg = function(loca){
 	d = {
 		1:"Inbox",
 		2:"Sent",
 		3:"Drafts",
 		4:"Junk",
 		5:"Trash"
 	}
 	w.sendmsg($('#compose-textarea').val(),$('#sendTo').val(),'send msg',$('#subjcT').val(),d[loca]);
 }
var webs =	class {
	constructor(){
	 var s =new WebSocket("ws://"+document.location.host+"/ws/chat");

	 s.sendaction = function (action){
		s.send(JSON.stringify({'action':action}));
	}
	 s.onopen = function (){
	s.sendaction("get list of msgs");

	}


	s.doupdate = function (data){

	}

	s.onmessage =  function (event){

  		var d = event.data;

  		var data = JSON.parse(d);
  		processResponse(data);
  		if (data.action =="plt_msges")
  			plt_msges(data);
  	}


	s.sendmsg = function (msg,receiver,action,subj,placedin){
		s.send(JSON.stringify({'action':action,'msg':msg,'subj':subj,'receiver':receiver,'placedin':placedin}))
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

/**

var pl = new pool(); 
pl.push(w);
setInterval(function(){
     	pl.forEach(update);
   },10000);

});

**/

