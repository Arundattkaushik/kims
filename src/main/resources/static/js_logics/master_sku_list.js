$(document).ready(function(){
	loadTableData();
})

function loadTableData(){
	$('#data-table').DataTable({
		
					paging: true,
				    scrollCollapse: true,
				    scrollY: '410px',
		
				ajax: {
									type: 'Get',
									url: '/msku-list',
									dataSrc: 'data'				
					},
						columns:[
								{data:"id", class:"text-start"},
								{data:"masterSkuTitle",
								render: function (data) {								 
								               return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="showModel(this)")>'+ data+'</a>';								                
								        }						
									},
								{data:"hsn"},
								{data:"masterSkuDescription"},								
								{data:()=>{
									return `<a onclick='deleteRow(this)' style="cursor: pointer"><img alt="delete-btn" src="img/delete.png" class="icon-s"></a>`
								}, class:"text-center"
							}
								
							],
							order: {
							       data:"invoiceNumber",
							        dir: 'asc'
							    }

				
			}			
			
	);
	
}




function getRowId(e){
	//$(e).parent().parent()
		let rowIndex = $(e).parent().parent().index();
		let selectedRow = $("#tbody tr")[rowIndex];
		let allCellsInSelectedRow = selectedRow.cells;
		let rowId = parseInt(allCellsInSelectedRow[0].innerHTML);
		return rowId
}


function showModel(e){
	document.getElementById('data-table').style.opacity=0.2;
	document.getElementById('pop-box').classList.add('popup-show');
	
	let mId = getRowId(e);
	$.ajax({
		type:'Post',
		url:'/get-master-by-id',
		data:{'id':mId},
		success:(data)=>{
			document.getElementById('masterSkuTitle').value=data.data.masterSkuTitle;
			document.getElementById('hsn').value=data.data.hsn;
			document.getElementById('masterSkuDescription').innerHTML=data.data.masterSkuDescription;
			
			let tbody = $('#tbody2');
			for(let i=0; i<data.data.rawSku.length; i++){
				tbody.append(`
				<tr>
				<td>
					<input readonly class="form-control" value="`+data.data.rawSku[i]+`"/>	
				</td>
				<td><input readonly type="number" class="form-control" id="rawskuquantity" name="rawskuquantity" value="`+data.data.rawskuquantity[i]+`"></td>
				</tr>				
				`);
				console.log(tbody);
				
			}
			
		},
		error:(errorThrown)=>{
			alert('Something went wrong!'+errorThrown)
		}
	})
}


function closeModel(){
	document.getElementById('pop-box').classList.remove('popup-show')
	document.getElementById('data-table').style.opacity=1;
}


function updateMaster(){
	//let x = getRowId(e);
	alert('x')
	
	
}

function deleteRow(e){
	let rId = getRowId(e);
	alert(rId)
	let delConfirm = confirm('Are you sure want to delete?');
	if(delConfirm==true){
		$.ajax({
				type:'post',
				url:'/delete-master',
				data:{"id":rId},
				success:function(){
					location.reload();
				}
			})			
	}	
}

