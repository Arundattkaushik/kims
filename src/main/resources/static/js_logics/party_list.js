$(document).ready(()=>{
	
	dataTable();
	
});


function dataTable(){
	$('#table-data').dataTable({
		
		ajax:{
			
			type:"Get",
			url:"/party/list",
			dataSrc: 'data'
		},
		columns:[
				 {data:"id", class:"text-start"},
				 {data:"name",
				 render: function (data) {								 
				                return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="showModel(this)")>'+ data+'</a>';								                
				         }						
				 	},
				 {data:"email"},
				 {data:"mobile"},								
				 {data:"address"},								
				 {data:(data)=>{	
					//console.log(data.rawSkuList.length)				
				 	return `<a onclick='deleteRow(this)' style="cursor:pointer;"><img alt="delete-btn" src="img/delete.png" class="icon-s"></a>`
				 }, class:"text-center"
			 }
			 	
			 ],
			 order: {
			        data:"invoiceNumber",
			         dir: 'asc'
			     }
		
		
	});
	 
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
	document.getElementById('pop-box').classList.add('popup-show')
	document.getElementById('table-data').style.opacity=0.2
}

function closeModel(){
	document.getElementById('pop-box').classList.remove('popup-show')
	document.getElementById('table-data').style.opacity=1
}

function deleteRow(e){
	let decision = confirm("Are you sure want to delete?")
	let pId = getRowId(e)
	if(decision==true){
		$.ajax({
			type:'post',
			url:'/party/delete',
			data:{'pId':pId},
			success:(res)=>{				
				if(res.message=='s'){
					alert('Party deleted successfully!');
					window.location.reload();
				}
				else if(res.message=='f'){
					alert("Cann't delete, Party is attached to a raw item!");
						window.location.reload();
				}
			},
			error:(errorThrown)=>{
				alert('something went wrong!'+errorThrown)
			}
		})
	}
}


