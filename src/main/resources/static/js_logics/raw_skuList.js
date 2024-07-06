$(document).ready(()=>{
	dataTable();
	$('label').css('color','yellow');
})

function dataTable(){
	$('#data-table').dataTable(); 

	$.ajax({
	       type: 'GET',
	       url: '/get-raw-sku-list'
		 });
}

function rSkuDetails(e){
	let rowId = parseInt(e.innerHTML)
	
	
	$.ajax({
					type: 'Post',
					url: '/get-row-sku-by-id',
					data:{'rId':rowId},			
					success: function(data) {
						/*console.log("Success block: "+data.name); */
						showPopUp(data)

					},
					error: function(errorThrown) {
						console.log("Error block run for => setBillToDetails() " + errorThrown)
					}
			});	
}

function showPopUp(data){
	document.getElementById('pop-box').classList.add('popup-show');
	document.getElementById('rId').innerHTML=data.id;
	document.getElementById('rTitle').innerHTML=data.name;
	document.getElementById('rAvlQty').innerHTML=data.quantity;
}

function calculateNewQuantity(){
	let oldqty = document.getElementById('rAvlQty').innerHTML;
	let addedQty = document.getElementById('rAddQty').value;
	let newQty = +(oldqty)+ +(addedQty);
	document.getElementById('rTotalQty').innerHTML=newQty;
}

function updateQuantity(){
	let rId = document.getElementById('rId').innerHTML;
	let rQty = document.getElementById('rTotalQty').innerHTML;
	
		$.ajax({
						type: 'Post',
						url: '/update-row-sku-quantity',								
						data:{'rQty':rQty,
								'rId':rId},			
						success: function(data) {
							if(data != null){
								closePopup();								
							}
						},
						error: function(errorThrown) {
							console.log("Error block run for => setBillToDetails() " + errorThrown)
						}
				});	
}

function closePopup(){
	document.getElementById('pop-box').classList.remove('popup-show');
}

