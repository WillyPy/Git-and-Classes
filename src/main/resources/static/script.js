window.onload = async function(){
    try{
        let res = await $.ajax({
            url: "/api/students",
            method: "post",
            dataType: "text"
        });
        document.getElementById("message").innerHTML = res;
    } 
    catch (error){
        console.log(error);
    }
}
async function addStudent(){
    try{
        let student = document.getElementById("student").value;
        let res = await $.ajax({
            url: "/api/students/"+student,
            method: "post",
            dataType: "text"
        });
        document.getElementById("message").innerHTML = res;
    } 
    catch (error){
        console.log(error);
    }
}
async function author(){
    try{
        let name = document.getElementById("name").value;
        let res = await $.ajax({
            url: "/api/students/"+student,
            method: "post",
            dataType: "text"
        });
        document.getElementById("message").innerHTML = res;
    } 
    catch (error){
        console.log(error);
    }
}