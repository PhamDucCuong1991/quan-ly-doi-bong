//hien thi acc
function show() {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        },
        url: "http://localhost:8081/users/listUser",
        //xử lý khi thành công
        success: function (users) {
            console.log(users)
            let str = `  <table class="table table-striped">
                        <h1 STYLE="color: #d58512">USER LIST</h1>
                        <thead>
                        <tr>
                            <th>&nbsp&nbsp&nbsp&nbsp id</th>
                            <th>&nbsp&nbsp&nbsp&nbsp Full Name</th>
                            <th>Username</th>
                            <th>&nbsp&nbsp&nbsp&nbsp&nbsp Authority Role</th>
                            <th>&nbsp&nbsp&nbsp&nbsp Action</th>
                            <th>&nbsp&nbsp Action</th>
                        </tr>
                        </thead>
                        <tbody>`;
            for (const user of users) {
                console.log(user)
                str += `  
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.fullName}</td>
                            <td>${user.username}</td>
                            <td>${user.roles[0].name}</td>
                            <td>
                                <a type="button" class="btn btn-primary"  onclick="accountManager(${user.id})"
                                >Detail
                                </a>
                            </td>
                            <td>
                                <a onclick="xoaUser(${user.id})" class="btn btn-danger">Xoa</a>
                            </td>
                        </tr> `
            }

            str +=     `</tbody>
                        </table>`

            document.getElementById("show").innerHTML = str;

        },
        error: function (err) {
            console.log(err)
        }
    })
    event.preventDefault();
}


if (localStorage.getItem("user").match("ADMIN")) {
    show();
}

function accountManager(id) {

    let img = "";
    let str = "";

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

            $("#id").val(user.id);
            $("#fullName").val(user.fullName);
            $("#username").val(user.username);
            $("#password").val(user.password);
            $("#address").val(user.address);
            $("#phoneNumber").val(user.phoneNumber);

             img += `
                      <img id="blah" src="${user.avatar}" width="120" class="rounded-circle" />
                      `


            // document.getElementById("showAvatar1").innerHTML = img;

             str += `
                  <div class="col-sm-3">
                     <h6 class="mb-0">Grant access</h6>
                     <select class="btn btn-primary" id="role">
                         <option value="1">ADMIN</option>
                         <option value="2">COACH</option>
                         <option value="3">PLAYER</option>
                         <option value="4">USER</option>
                     </select>
                  </div>`


            let role = user.roles[0].name;
            role = role.slice(5);
            document.getElementById("roleUser").innerHTML = role;
            document.getElementById("profileImg").innerHTML = img;
            document.getElementById("fullName1").innerHTML = user.fullName;
            document.getElementById("address1").innerHTML = user.address;
            document.getElementById("showRoles").innerHTML = str;


        },
        error: function (err) {
            console.log(err)
            alert("có lỗi")
        }
    });
    // event.preventDefault()
}

function xoaUser(id) {
    if(confirm("Bạn có chắc chắn muốn xóa tài khỏan này ???")=== false){
        return
    }
    if(parseInt(localStorage.getItem("id"))  === parseInt(id) ){
        alert("Bạn không thể xóa tài khoản này")
        return;
    }

    $.ajax({
        type: "DELETE",
        url: "http://localhost:8081/users/" + id,
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        },
        success: function (data) {
            alert("Xóa account thành công ")
            show()
        },
        error:function (err) {
            console.log(err)
            alert("Bạn không thể xóa account này")
        }
    })
}