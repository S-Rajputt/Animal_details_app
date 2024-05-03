const baseurl="http://localhost:8080";


const deleteUrl="http://localhost:8080/animal";

// acceess the button from html through queryselect and pass the ID 
//  and add event listener on this button to get the data from from backend 

let AnimalList=document.querySelector("#ListAnimal");

let container=document.getElementById("data");

let nameSort=document.querySelector("#namesort");

let categorySort=document.querySelector("#categorysort");

let ageSort=document.querySelector("#agesort");




// fethcing data from server through fetch api 


const fetchdata=async(getdata)=>{
    console.log("getting data from server")
    let response= await fetch(`${baseurl}/${getdata}`)

    let data = await response.json()
    console.log(data);
    renderData(data);
}

// delete the data 

const deletedata=async(id)=>{
    try{
    let response=await fetch(`${deleteUrl}/${id}` , {
        method:"DELETE",
    });
    if(response.status!=200){
        throw new Error(`somethin went wrong , status code:${response.status}`)
    }
    console.log("deleting a data ")
}catch(error){
    console.log(error);
}
   
}

// create a function update the data 



// create a arrow function to print the data on web and create a dynamic card for this 

    const renderData=(data)=>{

        //  here we clear the privious data from web page 

        container.innerHTML = ""; 
        form.innerHTML="";        


        // use for each loop to retrive the data one by one 
        // from server and print on web page

        data.forEach(element => {

            // here  i create a dynamic div and give the class name as card
            let card = document.createElement("div");
            card.className="card";
            

            let cardimg = document.createElement("img");
            cardimg.src = element.imagefile;
            card.appendChild(cardimg);
           
    
            let cardcontent=document.createElement("div");
            cardcontent.className="card-content";
            cardcontent.innerHTML=`<h3>${element.category}</h3>
            <h4>${element.name}</h4>
            <p>${element.description}</p>
            <p>${element.age}</p>`;

            card.appendChild(cardcontent);
            
            // create a update button 

            let updateBtn=document.createElement("button");
            updateBtn.innerText="update"
            updateBtn.className="update-btn"
            updateBtn.addEventListener("click" , ()=>{ 
            console.log("update button was  click")
            openUpdateForm(element);
            })

        //  append the button in card

            card.appendChild(updateBtn);

            // create a delete button 

            let deleteBtn=document.createElement("button");
            deleteBtn.innerText="Delete";
            deleteBtn.className="delete-btn";
            deleteBtn.addEventListener("click", ()=>{
                console.log("delete button was click");
                deletedata(element.id);
            })
            card.appendChild(deleteBtn);
           
            container.appendChild(card);
    
    
        })
    }

fetchdata("animal");


//  add event listener for retrive the data by using fetch data API

// name sort even listener

nameSort.addEventListener("click", ()=>{
    fetchdata("namesort");
})

//  category sort event listner 

categorySort.addEventListener("click", ()=>{
    fetchdata("categorysort");
})


//  age sort event listener 

ageSort.addEventListener("click", ()=>{
    fetchdata("agesort");
})


// create a function to add animal details 

let form = document.querySelector("#form");

let addBtn=document.querySelector("#addAnimal");

let appheading=document.querySelector(".heading");




const openAnimalForm=()=>{
    appheading.innerHTML="";
container.innerHTML="";
let animalForm= document.createElement("div");
animalForm.className="form-area"
animalForm.innerHTML= `
        <h2> Add Animal </h2>
        <form id="animal" enctype="multipart/form-data">
            <lable for="category">Select Animal Category</lable><br>
            <select id="category" name="category">
                <option value="Amphibians">Amphibians</option>
                <option value="Bird">Bird</option>
                <option value="Fish">Fish</option>
                <option value="Mammal">Mammal</option>
                <option value="Reptiles">Reptiles</option>
            </select><br>
                <lable for="name">Name</lable><br>
                <input type="text" placeholder="Enter Name" id="name" name="name" autocomplete="on" required/><br>


               
                <lable for="age">Age</lable><br> 
                <input type="number" placeholder="Enter Age" id="age" name="age" autocomplete="on" required/><br></br>
                
                <lable type="description">Description</lable><br>
                <input type="text" id="description" name="description" autocomplete="on" required/><br>

                <label for="image">Image</label><br>
                <input type="file" id="image" name="imagefile" required><br>

                <div class="g-recaptcha" data-sitekey="YOUR_SITE_KEY"></div><br> 

                <button type="button" onclick="generateAnimal()">Submit</button>
        </form>
`;
form.appendChild(animalForm);
}

const generateAnimal= async()=>{
    try{
    let form=document.querySelector("#animal")
    let formdata=new FormData(form);
    const data={
        category:formdata.get('category'),
        name:formdata.get('name'),
        age: parseInt(formdata.get('age')),
        description:formdata.get('description'),
        image:formdata.get('imagefile')
    }

    await postData(formdata);
    // console.log(newpost);
    console.log("new animal adding.......");

}catch(error){
    console.log(error);
}

}

// post the data on server through this form 


const postData = async (formdata)=>{
    try{
        
    let res=await fetch(`${baseurl}/animal`,{
        method:'POST',
        body: formdata
    });
    if(res.status!=200){
        throw new Error(`Something went wrong , status code:${res.status}`)
    }
    let responsedata=await res.json();
    console.log(responsedata);
    }catch(error){
        console.log(error);
    }
}


// open animal add  form 

addBtn.addEventListener("click", openAnimalForm);
