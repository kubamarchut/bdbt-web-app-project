const weights = [9,7,3,1,9,7,3,1,9,7];
const peselLEN = 11;
const peselInput = document.querySelector('input[name="pesel"]');
let sexInput = document.querySelectorAll('input[name="plec"]');
sexInput = {"M":sexInput[1], "K":sexInput[0]}
const birthDateInput = document.querySelector('input[name="data_urodzenia"]');
function decodePesel(pesel){
    let year = parseInt(pesel.substring(0, 2), 10);
    let month = parseInt(pesel.substring(2,4),10)-1;
    let day = parseInt(pesel.substring(4, 6), 10);

    if(month>80) {
        year = year + 1800;
        month = month - 80;
    }
    else{
        while(month > 20){
            month -= 20;
            year += 100;
        }
        year += 1900;
    }

    let birthdate=new Date();
    birthdate.setFullYear(year, month, day);

    pesel = pesel.split('').map(Number)


    let sum = 0;
    for (let i = 0; i<weights.length; i++){
        sum += pesel[i]*weights[i];
    }
    sum = sum % 10;
    let valid = sum === pesel[10];

    let sex = "M";
    if(pesel[9] % 2 == 0){
        sex = "K";
    }
    return {sex: sex, birthdate: birthdate, valid: valid};
}

peselInput.addEventListener("input", (e)=>{
    let inputPesel = e.target.value;
    if (inputPesel.length == peselLEN){
        let decodedInfo = decodePesel(inputPesel);
        if (!decodedInfo.valid) {
            e.target.classList.add("wrong");
            console.log(inputPesel.length, decodedInfo, inputPesel)
        }
        else {
            e.target.classList.remove("wrong");
            birthDateInput.value = decodedInfo.birthdate.toISOString().split('T')[0];

            for (const [key, btn] of Object.entries(sexInput)) {
                btn.checked = false;
            }
            sexInput[decodedInfo.sex].checked = true;
        }


    }

})
