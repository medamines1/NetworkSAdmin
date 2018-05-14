tmplate_for_dev = function (data,type) {
	data = JSON.parse(data);
	console.log(data);
	var d ={
		"computer":'laptop',
		"phone":'iphone',
		"printer":"printer",
	};
	return "<tr><td> <i class='ion-android-"+d[type]+"' name='"+data.nom+"' status='"+data.status+"' \
	id='"+type+"_id"+data.id+"' host='"+data.host+":"+data.port+"' onclick='show_data(this);' type='"+type+"'  data-toggle='modal' data-target='#modal-"+type+"'></i>"+data.nom +"</td></tr>"
}




