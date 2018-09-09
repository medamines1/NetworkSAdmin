
 var webs =  class {

 	constructor(){

 		return connect();
 	}

 }


 var w = new webs();


main_Data =$('#data-holder').data({
	'msg':{
		'inbox':{},
		'sent': {},
		'drafts':{},
		'junk':{},
		'trash':{},
	},
	'length_msg':{
		'inbox':0,
		'sent':0,
		'drafts':0,
		'junk':0,
		'trash':0,
	}
});


processResponse = function (data){
	console.log(data);
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


	stompClient.send("/app/messages/getAll",{},{});
	stompClient.subscribe("/topic/messages/getAll", function(data){
		plt_msges(data);
		});

	
	stompClient.subscribe("/topic/messages/send", function(data){
		processResponse(JSON.parse(data.body));
	});

	

	});

	return stompClient;
	}


set_length = function (){
	$('#length_msg_inbox').html(main_Data.data().length_msg.inbox);
	$('#length_msg_sent').html(main_Data.data().length_msg.sent);
	$('#length_msg_drafts').html(main_Data.data().length_msg.drafts);
	$('#length_msg_junk').html(main_Data.data().length_msg.junk);
	$('#length_msg_trash').html(main_Data.data().length_msg.trash);
}


plt_msges = function (data) {

	//
	delete data['action'];
	data = JSON.parse(JSON.parse(data.body).result);
	keys =Object.keys(data);

	for (i=0;i < keys.length;i++)
		{	
			key =keys[i];
			
			tmp = JSON.parse(data[key]);
			console.log("data:  ",data,"\n\n\n keysdata : ",Object.keys(tmp));
			if ( main_Data.data().msg[tmp.placedin][key] == undefined ){
				if (tmp.placedin=='sent' && tmp.sender==main_Data.data().id){
				main_Data.data().length_msg[tmp.placedin] ++;	
				main_Data.data().msg[tmp.placedin][key] = tmp;
				}
				else{
					main_Data.data().length_msg['inbox'] ++;	
					main_Data.data().msg['inbox'][key] = tmp;

				}

				set_length();
				if (tmp.placedin=='inbox' && (tmp.sender != main_Data.data().id) && (tmp.seen != '0')){
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
 
 
 sendAction = function (msg,receiver,subj,placedin){
 		w.send("/app/messages/send",{},JSON.stringify({msg:msg,subj:subj,receiver:receiver,placedin:placedin}))
 	}
 sendmsg = function(loca){
	 	d = {
	 		1:"inbox",
	 		2:"sent",
	 		3:"drafts",
	 		4:"junk",
	 		5:"trash"
	 	}
	 	sendAction($('#compose-textarea').val(),$('#sendTo').val(),$('#subjcT').val(),d[loca]);
	 }


 
 
 
