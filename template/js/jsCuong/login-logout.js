function loginUser() {

    let account = {
        "username": $("#username").val(),
        "password": $("#password").val()
    }

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8081/users/login",
        data: JSON.stringify(account),
        //xử lý khi thành công
        success: function (data) {

            localStorage.setItem("token", data.token);
            localStorage.setItem("username", data.username);
            localStorage.setItem("avatar", data.avatar);
            localStorage.setItem("role", data.role);
            localStorage.setItem("id", data.id);
            localStorage.setItem("user", JSON.stringify(data));
            alert("Đăng nhập thành công")

            location.href = "../index.html";

        },
        error: function (err) {
            console.log(err)
            alert("Mật khẩu hoặc tài khoản không đúng! ")
        }

    })
}

function logout() {
    localStorage.removeItem("token");
    location.href = "../index.html";
}


function onLoad() {

    if (localStorage.getItem("token")) {
        document.getElementById("login-logout").innerHTML = "<a href='login-logout/user_information.html'>Profile</a>"

        let str = `
                <div>
            Hello : <a  href="login-logout/user_information.html">${localStorage.getItem("username")}</a>
               /     <a href='' onclick='logout()'>Logout</a>
               </div>`

        document.getElementById("helloUser").innerHTML = str;
    } else {
        document.getElementById("login-logout").innerHTML = "<a href='login-logout/login.html' >login</a>"
    }

    if(localStorage.getItem("user").match("ADMIN")){
        document.getElementById("rollAdmin").innerHTML = "<a href=\"manager_admin.html\" title=\"\">MANAGERMENT</a>"
    }

    if (localStorage.getItem("user").match("COACH") ){
        document.getElementById("rollCoach").innerHTML = "<a href=\"coach.html\">COACH</a>"
    }
}

onLoad()

function accountDetail() {

    let id = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        },
        url: "http://localhost:8081/users/" + id,
        //xử lý khi thành công
        success: function (user) {
            console.log(user)
            document.getElementById("id").value = user.id;
            $("#fullName").val(user.fullName)
            $("#username").val(user.username);
            $("#password").val(user.password);
            $("#address").val(user.address);
            $("#phoneNumber").val(user.phoneNumber);
            $("#role").val(user.roles[0].id);

            let img = `
                      <img id="blah1" src="${user.avatar}" width="120" class="rounded-circle" />
                      `

            let showButton = `
                 <a type="button" class="btn btn-warning " onclick="upDateAvatar(${user.id})" href="#">Update</a>
                        `

            // document.getElementById("showAvatar").innerHTML = img;
            document.getElementById("profileImg").innerHTML = img;
            document.getElementById("fullName1").innerHTML = user.fullName;
            document.getElementById("address1").innerHTML = user.address;
            document.getElementById("showButton").innerHTML = showButton;
            let role = user.roles[0].name
            role = role.slice(5)
            document.getElementById("roleUser").innerHTML = role

        },
        error: function (err) {
            console.log(err)
            alert("Tài khoản của bạn đang bị tạm khóa")
        }
    })

    // event.preventDefault()
}
