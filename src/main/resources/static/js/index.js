$(document).ready(function () {
    $('#alarm').click(function(){
        alert('하이');
        $.ajax({
            url:'/alarm',
            method:'GET',
            dataType: 'json',
            success: function(e){
                console.log(e);
                alert(e);
            }
        });
    });
});