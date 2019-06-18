$(document).ready(function () {

    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();
    });

});

function fire_ajax_submit() {
    var data = $("#search-form").serializeArray();
    
    var formArray = {};
    
    $.map(data, function(n, i){
        formArray[n['name']] = n['value']; // key - value
    });

    var json =JSON.stringify(formArray)
    console.log(json);  // 데이터가 제대로 출력되는지 확인(출력) - F12
    $.ajax({
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        url: '/users',
        data: json,
        dataType: 'text',
        success: function (formArray) {
        	console.log("SUCCESS : ", formArray);  // 성공시 문구
        	window.location.href = "/users";  // get방식   등록 후 이 페이지로 감
        	
        },
        error: function (e) {
        	console.log("ERROR : ", e);
        }
    });

}