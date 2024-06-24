$(document).ready(function(){
	$("label").css("color","yellow");	
	$("label").css('font-weight','bold');	
	$("th").css("color","yellow");	
})

function addRow(){
		var v = $('#trow').clone().appendTo('#tbody');
		$(v).find("input").val('');
	}
	
function removeRow(e){
			$(e).parent().parent().remove();
    	}