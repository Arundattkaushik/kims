$(document).ready(()=>{
	dataTable();
	
})

function dataTable(){
	
	$('#data-table').dataTable({
							paging: true,
						    scrollCollapse: true,
							autoFill: true,							
						    scrollY: '415px',
		
		ajax: {
			       type: 'Get',
			       url: '/raw-party/list'
				   				  
			  },
				 columns:[
				 			{data:"rawSku.id", class:"text-start"},
				 			{data:"rawSku.name", render: function (data) {								 
				 							 return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="rSkuDetails(this)")>'+ data+'</a>';								                
				 						}						
				 					},
				 			{data:"party.name"},
				 			{data:"rawSku.quantity", class:"text-center"},								
				 			{data:"rawSku.description"},								
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
				url:'/rawsku/delete',
				data:{'rId': rId},
				success:(res)=>{					
						window.location.reload();
				},
				error:(res)=>{
					if(res.status === 403){
						alert("Can't delete an item if its attached to a party.");						
					}
					//window.location.href='/error';
					//window.location.reload();
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
					url: '/rawsku/get',
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
	document.getElementById('rId').innerHTML=data.data.id;
	document.getElementById('rTitle').innerHTML=data.data.name;
	document.getElementById('rAvlQty').innerHTML=data.data.quantity;
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
						url: '/rawsku/update',								
						data:{'rQty':rQty,
								'rId':rId},			
						success: function(res) {
							if(res != null){
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

