$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateResearcherForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidResearchIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "ResearcherAPI",  
			type : type,  
			data : $("#formResearcher").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onResearcherSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onResearcherSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divResearchGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidResearchIDSave").val("");  
	$("#formResearcher")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidResearchIDSave").val($(this).closest("tr").find('#hidResearchIDUpdate').val());     
	$("#rname").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#rDesp").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#rdate").val($(this).closest("tr").find('td:eq(2)').text());     
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "ResearcherAPI",   
		type : "DELETE",   
		data : "rid=" + $(this).data("researcherid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onResearcherDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onResearcherDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divResearchGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateResearcherForm() 
{  
	// NAME  
	if ($("#rname").val().trim() == "")  
	{   
		return "Insert Researcher Name.";  
	} 

	// DESCRIPTION------------------------  
	if ($("#rDesp").val().trim() == "")  
	{   
	return "Insert Description.";  
	} 
	
	
	//DATE-------------------------------
	if ($("#rdate").val().trim() == "")  
	{   
		return "Insert Date.";  
	} 

	return true; 
}