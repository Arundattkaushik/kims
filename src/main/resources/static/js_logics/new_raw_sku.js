$(document).ready(()=>{
	//Loading party list
	loadPartyList();
		
})

function createRawSku(){
	let form = $('#new_raw');
	let formData = form.serialize();

	
	$.ajax({
		type:'Post',
		url:'/rawsku/create',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data:formData,
		success:(response)=>{
		if(response.status === 'OK') {
			alert("Raw SKU created successfully!");
			window.location.href='raw-sku-list';
			    } else {
			        alert("Failed to create Raw SKU!");
					window.location.href='/new-raw-sku';
			    }
		},
		error:()=>{
			alert("Error while creating Raw SKU!");
			window.location.href='/error';
		}
	})
   
}


function loadPartyList(){
	//alert('hello gorillas')
	$.ajax({
	            type: 'Get',
	            url: '/party/list',
	            
	            success: function (res) {
	                /*alert("Success block: "+res.data.length);*/
	                
					if(res == null || res == ''){
						console.log(errorThrown)
					}
					else{				
					for(let i=0; i<res.data.length; i++){ 
						
						$('#partyId').append(
							'<option value="'+ res.data[i].id +'">' + res.data[i].name + "</option>"
										);
					}
				}
	                
	           },
	           error: function (errorThrown) {
	           		alert("Something went wrong! "+errorThrown)
	           }
	    });
}