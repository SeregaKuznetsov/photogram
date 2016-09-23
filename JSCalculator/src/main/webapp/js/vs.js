src="http://code.jquery.com/jquery-latest.js"
src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"


$('form').validate({
    rules: {
        text: {
            required: true,
            regexp: '^-?[0-9]+([.,]?)[0-9]*$'
        }
    },
    messages: {
        text: {
            required: "Заполни плиззз",
            regexp: "Need only numbers!"
        }
    }
});

$.validator.addMethod('regexp', function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
);

$.ajax({
    url: 'test.html',
    success: function(){
        alert('Load was performed.');
    }
});