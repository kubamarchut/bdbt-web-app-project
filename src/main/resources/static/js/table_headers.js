const colChooser = document.querySelector("div.col-choose");
const colChooserBTN = colChooser.parentElement.querySelector("button");
const tableRows = document.querySelectorAll("tr");

colChooserBTN.addEventListener('click', ()=>{colChooser.parentElement.classList.toggle("active")});

let tableHeaders = document.querySelector("tr").querySelectorAll("th");
tableHeaders = Array.prototype.slice.call(tableHeaders);


tableHeaders.forEach((item, index, arr) => {
    arr[index] = item.textContent;
})
tableHeaders.forEach((item) => {
    let id = item.replaceAll(" ", "");
    let newCheckBox = document.createElement("input");
    newCheckBox.type = "checkbox";
    newCheckBox.checked = true;
    newCheckBox.id = id;
    newCheckBox.value = item;
    newCheckBox.classList.add("custom-checkbox");
    newCheckBox.addEventListener("change", hideShowCol);

    let newLabel = document.createElement("label");
    newLabel.htmlFor = id;
    newLabel.innerHTML = '<span class="checkbox"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path class="tick" d="m4.2 8 2.53 2.54 5.08-5.08" fill="none" stroke-linecap="round"stroke-linejoin="round" stroke="#fff" stroke-width="1.6"/> </svg> </span>'
    newLabel.innerHTML += item;

    colChooser.appendChild(newCheckBox);
    colChooser.appendChild(newLabel);
})
function onTogglePress(e){
    if(e.target.checked){
        e.target.classList.add("check");
        e.target.classList.remove("unCheck");
    }
    else{
        e.target.classList.remove("check");
        e.target.classList.add("unCheck");
    }
}
function hideShowCol(e){
    onTogglePress(e);
    let index = tableHeaders.indexOf(e.target.value) + 1;
    tableRows.forEach((item) => {
        let hidingCol = item.querySelector(":nth-child("+index+")");

        if (e.target.checked) hidingCol.style.display = "table-cell";
        else hidingCol.style.display = "none";

    })
}

const rowSearchControls = document.querySelector("div.row-search");
const rowSearchSelect = rowSearchControls.querySelector("select");
const rowSearchInput = rowSearchControls.querySelector("input");
const rowSearchCountOutput = rowSearchControls.querySelector("span");


tableHeaders.forEach((header, index) =>{
    let newOption = document.createElement("option");
    newOption.value = index;
    newOption.innerHTML = header;

    if(header != "Akcje")rowSearchSelect.appendChild(newOption);
})

rowSearchInput.addEventListener("input", search);
rowSearchSelect.addEventListener("change", (e)=>{
    tableRows.forEach((item) => {
        highlight("", item)
    })
    search(e);
});
function highlight(text, cell) {
    let innerHTML = cell.innerHTML;
    innerHTML = innerHTML.replaceAll("<mark>", "").replaceAll("</mark>", "");
    let index = text != "" ? innerHTML.indexOf(text) : -1;
    if (index >= 0) {
        innerHTML = innerHTML.substring(0,index) + "<mark>" + innerHTML.substring(index,index+text.length) + "</mark>" + innerHTML.substring(index + text.length);
        
    }
    cell.innerHTML = innerHTML;
}
let lastCol = -1;
function search(){
    let query = rowSearchInput.value;
    let hidden = 0;

    tableRows.forEach((row, i) => {
        if (i == 0) {
            hidden = 0;
            return;
        }
        let cell = row.querySelectorAll("td")[parseInt(rowSearchSelect.value)];

        if (!cell.innerText.includes(query)){
            row.style.display = "none";
            hidden++;
        }
        else row.style.display="table-row";

        highlight(query, cell);
    });

    let countText = `Wyświetlane <span style="font-weight: 900">${tableRows.length - hidden - 1}</span> z ${tableRows.length - 1}`;
    rowSearchCountOutput.innerHTML = countText;
    if (query == "") rowSearchCountOutput.innerHTML = "";
    lastCol = parseInt(rowSearchSelect.value);
}