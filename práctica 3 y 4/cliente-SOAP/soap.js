var mensaje="";
function ini(){
    if(document.getElementById('Operaciones').value=="suma"){
        mensaje = '<?xml version="1.0" encoding="utf-8"?>' +
        '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">' +
        '<Body>' +
        '<SumarRequest xmlns="http://www.example.org/calculadora">' +
        '<a>'+ document.getElementById('a').value +'</a>' +
        '<b>'+ document.getElementById('b').value +'</b>' +
        '</SumarRequest>' +
        '</Body>' +
        '</Envelope>';
    }else{
        if(document.getElementById('Operaciones').value=="resta"){
            mensaje = '<?xml version="1.0" encoding="utf-8"?>' +
            '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">' +
            '<Body>' +
            '<RestarRequest xmlns="http://www.example.org/calculadora">' +
            '<a>'+ document.getElementById('a').value +'</a>' +
            '<b>'+ document.getElementById('b').value +'</b>' +
            '</RestarRequest>' +
            '</Body>' +
            '</Envelope>';
        }else{
            if (document.getElementById('Operaciones').value=="division") {
                mensaje = '<?xml version="1.0" encoding="utf-8"?>' +
                '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">' +
                '<Body>' +
                '<DividirRequest xmlns="http://www.example.org/calculadora">' +
                '<a>'+ document.getElementById('a').value +'</a>' +
                '<b>'+ document.getElementById('b').value +'</b>' +
                '</DividirRequest>' +
                '</Body>' +
                '</Envelope>';
            }else{
                if (document.getElementById('Operaciones').value=="multiplicacion") {
                    mensaje = '<?xml version="1.0" encoding="utf-8"?>' +
                        '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">' +
                        '<Body>' +
                        '<MultiplicarRequest xmlns="http://www.example.org/calculadora">' +
                        '<a>'+ document.getElementById('a').value +'</a>' +
                        '<b>'+ document.getElementById('b').value +'</b>' +
                        '</MultiplicarRequest>' +
                        '</Body>' +
                        '</Envelope>'; 
                }else{
                    window.alert("Texto a mostrar");
                    alert("Texto a mostrar");
                }
            }
        }
    }
    
}


function soap() {
    // alert('hola')
    ini();
    axios.post('http://localhost:8080/ws/calculadora',mensaje,{
        headers:{
            'Content-Type' : 'text/xml'
        }
    })
    .then(function (response){
        document.getElementById('r').value= resultado(response.data)
    })
    .catch(err => console.log(err));
}

function resultado(rXml){
    var parser = new DOMParser();
    var xmlDoc= parser.parseFromString(rXml, "text/xml");
    var resul= xmlDoc.getElementsByTagName("ns2:resultado")[0].childNodes[0].nodeValue;
    return resul;
}