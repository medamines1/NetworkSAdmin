/**
function loadit(){
$.get("update_machine",function(data){

	var a = $("#plt_pc");                          
    $("#plt_pc").find('option').remove();                    
    $.each(data, function(key, value) {      
    	console.log(key);
    	console.log(value);
        $('<option>').val(key).text(value).appendTo(a); 
    });
});

}
**/


//$(document).ready(function(){
	//setInterval(function(){loadit();},5000)});
