function myCheckForm(digit) {
    valid =  true;
    re = /^-?[0-9]+([.,]?)[0-9]*$/;
    if (!re.test(digit)) {
        //alert("Error: Must contain only numbers");
        //document.getElementById('error').innerHTML = 'Error: Must contain only numbers!'
        form.text.focus();
        valid =  false;
    }
    return valid;
}

function myAjax(digit, action) {
    if (myCheckForm(digit)) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "test?digit=" + digit + "&action=" + action, false);
        xhttp.send();
        document.getElementById("result").innerHTML = xhttp.responseText;
        clearText();
    }
}

function clearText() {
    $('#text').val('');
}


