$(document).ready(()=>{
	dataTable();
	
})

function dataTable(){
	
	$('#data-table').dataTable({
							paging: true,
						    scrollCollapse: true,
							autoFill: true,							
						    scrollY: '400px',
		
		ajax: {
			       type: 'Get',
			       url: '/get-raw-sku-list',
				 },
				 columns:[
				 			{data:"id", class:"text-start"},
				 			{data:"name",
				 			render: function (data) {								 
				 								               return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="rSkuDetails(this)")>'+ data+'</a>';								                
				 								        }						
				 									},
				 			{data:"party_list"},
				 			{data:"quantity", class:"text-center"},								
				 			{data:"description"},								
				 			{data:()=>{
				 									return `<a onclick='deleteRow(this)' style="cursor: pointer"><img alt="delete-btn" src="img/delete.png" class="icon-s"></a>`
				 			}, class:"text-center"
				 		}
				 								
				 		],
				 order: {
				        data:'id',
				         dir: 'desc'
				  }
		
	}); 
}



function deleteRow(e){
	let x = confirm('Are you sure want to delete?');
	if(x==true){
			let rId = getRowId(e);
			$.ajax({
				type:'post',
				url:'/delete-rowsku-by-id',
				data:{'rId': rId},
				success:()=>{
					window.location.reload();
				},
				error:()=>{
					alert('Something went wrong!');
				}
			})
	}
	
}


function getRowId(e){
	//$(e).parent().parent()
		let rowIndex = $(e).parent().parent().index();
		let selectedRow = $("#tbody tr")[rowIndex];
		let allCellsInSelectedRow = selectedRow.cells;
		let rowId = parseInt(allCellsInSelectedRow[0].innerHTML);
		return rowId
}



function rSkuDetails(e){
	let rowId = getRowId(e);
	
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
	document.getElementById('table-data').style.opacity=0.3
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
								window.location.reload();								
							}
						},
						error: function(errorThrown) {
							console.log("Error block run for => setBillToDetails() " + errorThrown)
						}
				});	
}

function closePopup(){
	document.getElementById('pop-box').classList.remove('popup-show');
	document.getElementById('table-data').style.opacity=1;
}

