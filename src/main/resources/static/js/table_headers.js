const colChooser = document.querySelector("div.col-choose");
const colChooserBTN = colChooser.parentElement.querySelector("button");
const tableRows = document.querySelectorAll("tr");

colChooserBTN.addEventListener('click', ()=>{colChooser.classList.toggle("active")});

let tableHeaders = document.querySelector("tr").querySelectorAll("th");
tableHeaders = Array.prototype.slice.call(tableHeaders);


tableHeaders.forEach((item, index, arr) => {
    arr[index] = item.textContent;
})
tableHeaders.forEach((item) => {
    let linebreak = document.createElement("br");
    let id = item.replaceAll(" ", "");
    let newCheckBox = document.createElement("input");
    newCheckBox.type = "checkbox";
    newCheckBox.checked = true;
    newCheckBox.id = id;
    newCheckBox.value = item;
    newCheckBox.addEventListener("change", hideShowCol);

    let newLabel = document.createElement("label");
    newLabel.htmlFor = id;
    newLabel.innerHTML = item;

    colChooser.appendChild(newCheckBox);
    colChooser.appendChild(linebreak);
    colChooser.appendChild(newLabel);
    colChooser.appendChild(linebreak);
})

function hideShowCol(e){
    let index = tableHeaders.indexOf(e.target.value) + 1;
    tableRows.forEach((item) => {
        let hidingCol = item.querySelector(":nth-child("+index+")");

        if (e.target.checked) hidingCol.style.display = "table-cell";
        else hidingCol.style.display = "none";

    })
}