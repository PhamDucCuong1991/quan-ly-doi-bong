
    function homeCoach(){
        displayTypicalCoach()
        displayAllCoach(0)
    }
    window.onload = homeCoach()
    //Hiển thị HLV tiêu biểu bên Coach
    function displayTypicalCoach(){
        $.ajax({
            url : "http://localhost:8081/coaches/typical",
            type: "GET",
            success(data){
                let context = ""
                for (let i = 0; i < data.length; i++) {
                    context += `<div class="col-sm-12 col-md-4">
                                        <div class="coach-item">
                                          <div class="gambar">
                                            <img src="${data[i].imagePath}" style="width: 500px; height: 500px" alt="Coach" class="img-responsive">
                                          </div>
                                          <div class="item-body">
                                            <div class="name">
                                              ${data[i].name}
                                            </div>
                                            <div class="position">
                                              ${data[i].position.name}
                                            </div>
                                          </div>
                                        </div>
                                      </div>`
                }
                document.getElementById("typicalCoach").innerHTML = context;
            }
        })
    }
    //Hiển thị HLV tiêu biểu bên admin
    function displayTypicalCoachAdmin(){
        $.ajax({
            url : "http://localhost:8081/admin/coaches/typical",
            type: "GET",
            success(data){
                let context = ""
                for (let i = 0; i < data.length; i++) {
                    context += `<div class="col-sm-12 col-md-4">
                                    <div class="coach-item">
                                      <div class="gambar">
                                        <img src="${data[i].imagePath}" style="width: 500px; height: 500px" alt="Coach" class="img-responsive">
                                      </div>
                                      <div class="item-body">
                                        <div class="name">
                                          ${data[i].name}
                                        </div>
                                        <div class="position">
                                          ${data[i].position.name}
                                        </div>
                                      </div>
                                    </div>
                                  </div>`
                }
                document.getElementById("typicalCoach").innerHTML = context;
            }
        })
    }
    //Hiển thi danh sách tất cả HLV
    function displayAllCoach(page){
        $.ajax({
            url : "http://localhost:8081/coaches?page=" + page + "&size=3",
            type : "GET",
            success(data){
                displayTableCoach(data.content)
                displayCoachPage(data)
                if (data.pageable.pageNumber === 0) {
                    document.getElementById("backup").hidden = true
                }
                //điều kiện bỏ nút next
                if (data.pageable.pageNumber + 1 === data.totalPages) {
                    document.getElementById("next").hidden = true
                }
                $("#coachForm").hide()
                $("#creatFormCoach").show()
            }
        })
    }
    //Hiển thi danh sách tất cả HLV
    function displayAllCoachAdmin(page){
        $.ajax({
            url : "http://localhost:8081/admin/coaches?page=" + page + "&size=3",
            type : "GET",
            success(data){
                displayTableCoach(data.content)
                displayCoachPage(data)
                if (data.pageable.pageNumber === 0) {
                    document.getElementById("backup").hidden = true
                }
                //điều kiện bỏ nút next
                if (data.pageable.pageNumber + 1 === data.totalPages) {
                    document.getElementById("next").hidden = true
                }
            }
        })
    }

    //Hiển thị bảng HVL
    function  displayTableCoach(data){
        let context = `<div class="container">            
                <table class="table table-striped-columns">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Nation</th>
                        <th>Work Position</th>
                        <th>Hard Salary</th>
                        <th>Bonus Salary</th>              
                    </tr>
                    </thead>
                    <tbody>`
            for (let i = 0; i < data.length; i++) {
                context += `<tr>
                            <td>${i+1}</td>
                            <td><img src="${data[i].imagePath}" alt="Coach" style="width: 50px"; height="50px"></td>
                            <td>${data[i].name}</td>
                            <td>${data[i].date}</td>
                            <td>${data[i].address}</td>
                            <td>${data[i].position.name}</td>
                            <td>${data[i].sumHardSalary}</td>
                            <td>${data[i].sumBonusSalary}</td>
                            </tr>`
            }
        context += `</tbody> </table> </div>`
        document.getElementById("showCoach").innerHTML = context;
    }
    function displayCoachPage(data){
        let content = `<button class="btn btn-primary" id="backup" onclick="isPreviousCoach(${data.pageable.pageNumber})">Previous</button>
    <span>${data.pageable.pageNumber+1} | ${data.totalPages}</span>
    <button class="btn btn-primary" id="next" onclick="isNextCoach(${data.pageable.pageNumber})">Next</button>`
        document.getElementById('pageCoach').innerHTML = content;
    }
    //hàm lùi page
    function isPreviousCoach(pageNumber) {
        displayAllCoachAdmin(pageNumber-1)
    }

    //hàm tiến page
    function isNextCoach(pageNumber) {
        displayAllCoachAdmin(pageNumber+1)
    }




