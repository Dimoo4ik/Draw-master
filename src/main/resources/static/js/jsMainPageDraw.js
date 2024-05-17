function openFormEntrance() {
  document.getElementById('container_for_form_entrance').style.display = 'flex';
}

function openForm() {
  document.getElementById('container_for_form').style.display = 'flex';
}

function view_password() {
  document.getElementById('password').setAttribute('type', 'text');
}

function view_password_entrance() {
  document.getElementById('password_entrance').setAttribute('type', 'text');
}

//Load photo user
$("#load_photo").change(function() {
     if (this.files && this.files[0]) {
      var reader = new FileReader();
      reader.onload = function(e) {
      var inputImage = document.getElementById('load_photo');
      inputImage = e.target.result;

      var newImage = document.getElementById('image_photo');
      newImage.src = inputImage;
      newImage.style.width="150px";
      newImage.style.height="150px";
      newImage.style.borderRadius="150px";
      newImage.style.border="black 2px solid";
      newImage.style.objectFit="cover";
   }
     reader.readAsDataURL(this.files[0]);
  }
});

//Close window for entrance
function close_form_registration() {
  let email = document.getElementById('email');
  let clear_email = email.reset();
  let password = document.getElementById('password');
  let clear_password = password.reset();
  document.getElementById('password_for_repeat').reset();
  let registration = document.getElementById("name_form_for_registration");
  document.getElementById('container_for_form').style.display = 'none';
}
    //Adding user
     $("#button_form_registration_user").click(function() {
             var data = $("#container_for_form form").serialize();
             $("#container_for_form").css("display", "none");
             $.ajax({
                 method: "POST",
                 url: "/addUser/",
                 data: data,
                 success: function(response) {
                     $("#container_for_form").css("display", "none");
                     var user = {};
                     user.id = response;
                     var dataArray = $("#container_for_form form").serializeArray();
                     for(i in dataArray) {
                         user[dataArray[i]["email"]] = dataArray[i]["value"];
                     }
                     document.getElementById('password').setAttribute('type', 'password');
                     document.getElementById("name_form_for_registration").reset();
                 }
             });
             return false;
       });

//update data about user
$("#button_save").click(function() {
  var dataFull = $("section form").serialize();
     $.post('/updateInformationUser/', dataFull, function(response) {
        var user = {};
        var dataArray = $("section form").serializeArray();
        var input = document.querySelector('input[type="file"]');
        var dataImage = new FormData();
        dataImage.append('file', input.files[0])
        for(i in dataArray) {
           user[dataArray[i]["fullName"]] = dataArray[i]["value"];
        }
        fetch('/addImage/', {
          method: 'POST',
          body: dataImage
        })
     });
     return false;
  });