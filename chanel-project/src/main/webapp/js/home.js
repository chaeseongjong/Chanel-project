$(document).ready(function(){
	$('#portfolioDownload').click(function(){
		$.ajax({
		    type: "GET",
		    url: "/download",
		    data: "",
		    cache: false,
		    success: function(response)
		    {
		        alert('got response');
		    },
		    error: function (XMLHttpRequest, textStatus, errorThrown) 
		    {
		        alert('Error occurred while opening fax template'+ getAjaxErrorString(textStatus, errorThrown));
		    }
		});
	});
});