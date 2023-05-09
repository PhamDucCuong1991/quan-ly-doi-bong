
function showAllPlayer(page) {
    let search = $("#search").val()
    $.ajax({
        url: "http://localhost:8081/players/home",//?page=" + page + "&search=" + search,
        type: 'GET',
        dataType: 'json',
        success(data) {
            let arr = data.pagePlayer.content
            console.log(search)
            displayPlayer(arr)
            displayPage(data.pagePlayer)

            if (data.pagePlayer.pageable.pageNumber === 0) {
                document.getElementById("backup").hidden = true
            }
            if (data.pagePlayer.pageable.pageNumber + 1 === data.pageProduct.totalPages) {
                document.getElementById("next").hidden = true
            }
        }
    })
    event.preventDefault()
}
function displayPlayer(arr) {
    let context = `<table class="table table-hover"
                            style="text-align: center">
                            <tr>
                            <th>STT</th>
                            <th>Name</th>
                            <th>Date</th>
                            <th>Address</th>
                            <th>Weight</th>
                            <th>Height</th>
                            <th>Img</th>
                            <th colspan="2">Action</th>
                            </tr>`
    for (let i = 0; i < arr.length; i++) {
        context += `<tr>
                            <td>${i + 1}</td>
                            <td>${arr[i].name}</td>
                            <td>${arr[i].date}</td>
                            <td>${arr[i].address}</td>
                            <td>${arr[i].weight}</td>
                            <td>${arr[i].height}</td>
                            <td><img src="${arr[i].img}" alt="" width="100px" height="100px"></td>
                            <td><button onclick="updateForm(${arr[i].id})" class="btn btn-success">Update</button></td>
                            <td><button onclick=" deleteForm(${arr[i].id})" class="btn btn-danger">Delete</button></td>
                            </tr>`
    }
    context += `</table>`
    document.getElementById("display").innerHTML = context
}
function displayPage(data) {
    let content =
        `<button class="btn btn-primary" id="backup" onclick="isPrevious(${data.pageable.pageNumber})">Previous</button>
    <span>${data.pageable.pageNumber + 1} | ${data.totalPages}</span>
    <button class="btn btn-primary" id="next" onclick="isNext(${data.pageable.pageNumber})">Next</button>`
    document.getElementById('page').innerHTML = content;
}
function isPrevious(pageNumber) {
    showAllPlayer(pageNumber - 1)
}
function isNext(pageNumber) {
    showAllPlayer(pageNumber + 1)
}



function updateForm(id){}
function deleteForm(id){}


function showMainLineup() {
    $.ajax({
        url: "http://localhost:8081/players/teams",
        type: 'GET',
        dataType: 'json',
        success(data) {
            let mainLineup = data.pagePlayer.content
            let subLineup = data.pagePlayer.content
            console.log(data)
        }
    })
    event.preventDefault()
}

showAllPlayer(0)