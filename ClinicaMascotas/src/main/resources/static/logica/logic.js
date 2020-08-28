$(function(){
    verDetalles();
    editarPropietario();
    traerDatos();
    guardarPropietario();
    guardarMascota();
    mostrarMascota();
    editarMascota();
    mascotaEditada();
});

function traerDatos(){
    $.ajax({
        url: '/propietarios',
        type: 'GET',
        dataType: 'json',
        success : function(json){
            $.each(json, function( k, v ) {                
            $("#listado").append(
                '<tr class="temporal">'+
                    '<td>'+v.id+'</td>'+
                    '<td>'+v.nombres+'</td>'+
                    '<td>'+v.apellidos+'</td>'+
                    '<td>'+v.direccion+'</td>'+
                    '<td>'+v.telefono+'</td>'+
                    '<td>'+'<a href="#" class="detalles">Ver Detalles</a>'+'</td>'+                                                     
                '</tr>'
            )
            $("#listadoM").append(
                '<tr class="temporal">'+
                    '<td>'+v.id+'</td>'+
                    '<td>'+v.nombres+'</td>'+
                    '<td>'+v.apellidos+'</td>'+
                    '<td>'+v.direccion+'</td>'+
                    '<td>'+v.telefono+'</td>'+
                    '<td>'+'<a href="#" class="nueva-mascota small" >Agregar Mascota</a>'+'</td>'+                                                     
                '</tr>'
            )
            });
        },
    });
}

function guardarPropietario(){
    $('form #guardarP').on('click', function(e){
        e.preventDefault();

        if(($("#nombres").val().length < 1) || ($("#apellidos").val().length < 1) || ($("#direccion").val().length < 1) || ($("#telefono").val().length < 1)) {  
            alert("Todos los campos son obligatorios");  
            return false;  
        }

        var nombres = $('#nombres').val();
        var apellidos = $('#apellidos').val();
        var direccion = $('#direccion').val();
        var telefono = $('#telefono').val();

        

        $.ajax({
            url: '/crear-propietario',
            type: 'POST',        
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept","application/json");
            },
            contentType:'application/json',
            dataType:'json',
            data:JSON.stringify({"nombres":nombres,"apellidos":apellidos,"direccion":direccion,"telefono":telefono}),
                success : function(json){
                
                    swal("Listo!", "El propietario se ha guardado", "success");
                    $(".temporal").remove();
                    traerDatos();
                    nombres.val('');
                    apellidos.val('');
                    direccion.val('');
                    telefono.val('');
                },           
        });
    });
}


function verDetalles(){
    $('#u-tabla').on('click', '.detalles', function(e){
        e.preventDefault();        
        
        var tr = $(this).closest('tr');
        var tdId = tr.children("td:nth-child(1)");
        var tdNombres = tr.children("td:nth-child(2)");
        var tdApellidos = tr.children("td:nth-child(3)");
        var tdDireccion = tr.children("td:nth-child(4)");
        var tdTelefono = tr.children("td:nth-child(5)");

        var vId = tdId.html();
        var vNombres = tdNombres.html();
        var vApellidos = tdApellidos.html();
        var vDireccion = tdDireccion.html();
        var vTelefono = tdTelefono.html();        

        $("#id-lp").prop("disabled", false);
        $("#nombres-lp").prop("disabled", false);
        $("#apellidos-lp").prop("disabled", false);
        $("#direccion-lp").prop("disabled", false);
        $("#telefono-lp").prop("disabled", false);

        $("#id-lp").val(vId);
        $("#nombres-lp").val(vNombres);
        $("#apellidos-lp").val(vApellidos);
        $("#direccion-lp").val(vDireccion);
        $("#telefono-lp").val(vTelefono);
        
        //Mostrar mascotas
        $.ajax({
            url: '/mascota-propietario/'+vId,
            type: 'GET',
            dataType: 'json',
            success : function(json){
            	$(".temporalM").remove();  
                $.each(json, function( k, v ) {                
                    $("#listMascotas").append(
                    	                  	
                        '<tr class="temporalM">'+                            
                            '<td>'+v.nombre+'</td>'+
                            '<td>'+v.tipo.displayName+'</td>'+ 
                            '<td>'+v.fechaNac+'</td>'+                                                                                
                            '<td>'+'<a href="#" class="editar-m">Editar</a>'+'</td>'+ 
                            '<td class="collapse">'+v.id+'</td>'+                                              
                        '</tr>'
                    )
                });
            },
        });

    });
}

function editarMascota(){
    $('#m-tabla').on('click', '.editar-m', function(e){
        e.preventDefault();
            
        var tr = $(this).closest('tr');
        var tdNombreM = tr.children("td:nth-child(1)");    
        var tdFechaM = tr.children("td:nth-child(3)");
        var tdOpcion = tr.children("td:nth-child(4)");

        
        var vNombreM = tdNombreM.html();
        tdNombreM.html("<input type='text' class='edi-mas-nombre' value='" + vNombreM + "'/>");
        var vFechaM = tdFechaM.html();
        tdFechaM.html("<input type='text'  class='edi-mas-fecha' value='" + vFechaM +"'/>");

        tdOpcion.html("<a href='#' class='guardar-m'>Guardar</a>");
    });
}

function mascotaEditada(){
    $('#m-tabla').on('click', '.guardar-m', function(e){
        e.preventDefault();

        var tr = $(this).closest('tr');
        var tdNombreM = tr.children("td:nth-child(1)");
        var tdTipo = tr.children("td:nth-child(2)");
        var tdFechaM = tr.children("td:nth-child(3)");    
        var tdOpcion = tr.children("td:nth-child(4)");
        var tdIdMascota = tr.children("td:nth-child(5)");

        var idPropietario = $('#id-lp').val();
        var nuevoNombre = $(".edi-mas-nombre").val();        
        var nuevaFecha = $(".edi-mas-fecha").val();                
        var tipo = tdTipo.html();
        

        var idMascota = tdIdMascota.html();
        
        /*
        tdNombreM.html(nuevoNombre);                

        tdOpcion.html("<a href='#' class='editar'>Editar</a>");
        */

       $.ajax({
        url: '/mascota-propietario/'+idPropietario,
        type: 'PUT',        
        beforeSend: function(xhr){
            xhr.setRequestHeader("Accept","application/json");
        },
        contentType:'application/json',
        dataType:'json',
        data:JSON.stringify({"id":idMascota,"nombre":nuevoNombre,"tipo":tipo.toUpperCase(),"fechaNac":nuevaFecha}),
            success : function(json){
            
                swal("Listo!", "La mascota se ha editado", "success");                

                $.ajax({
                    url: '/mascota-propietario/'+idPropietario,
                    type: 'GET',
                    dataType: 'json',
                    success : function(json){
                        $(".temporalM").remove();  
                        $.each(json, function( k, v ) {                
                            $("#listMascotas").append(
                                                      
                                '<tr class="temporalM">'+                            
                                    '<td>'+v.nombre+'</td>'+
                                    '<td>'+v.tipo.displayName+'</td>'+ 
                                    '<td>'+v.fechaNac+'</td>'+                                                                                            
                                '</tr>'
                            )
                        });
                    },
                });
            },           
    });

    });
}



function editarPropietario(){
    $('form #edit-propietario').on('click', function(e){
        e.preventDefault();
        
        if(($("#nombres-lp").val().length < 1) || ($("#apellidos-lp").val().length < 1) || ($("#direccion-lp").val().length < 1) || ($("#telefono-lp").val().length < 1)) {  
            alert("Todos los campos son obligatorios");  
            return false;  
        }
        var id = $('#id-lp').val();
        var nombres = $('#nombres-lp').val();
        var apellidos = $('#apellidos-lp').val();
        var direccion = $('#direccion-lp').val();
        var telefono = $('#telefono-lp').val();

        $.ajax({
            url: '/editar-propietario',
            type: 'PUT',        
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept","application/json");
            },
            contentType:'application/json',
            dataType:'json',
            data:JSON.stringify({"id":id,
                                "nombres":nombres,
                                "apellidos":apellidos,
                                "direccion":direccion,
                                "telefono":telefono}),
                success : function(json){
                
                    swal("Listo!", "El propietario se ha editado", "success");
                    $(".temporal").remove();           
                    traerDatos();
                
                },           
        });

    });
}

function guardarMascota(){
    $('#i-tabla').on('click', '.nueva-mascota', function(e){
        e.preventDefault();
        $(".ocultar").show("fast");

        var tr = $(this).closest('tr');
        var tdId = tr.children("td:nth-child(1)");
        var tdNombres = tr.children("td:nth-child(2)");
        var tdApellidos = tr.children("td:nth-child(3)");

        var vId = tdId.html();
        var vNombres = tdNombres.html();
        var vApellidos = tdApellidos.html();

        $('#id-tomar').val(vId);
        $('#propietario-mascota').val(vNombres+" "+vApellidos);
        $(".temporalM").remove(); 
    });
}

function mostrarMascota(){
    $("form #guardarM").on('click', function(e){
        e.preventDefault();
        

        if(($("#nombre-mascota").val().length < 1) || ($("#tipo").val().length < 1) || ($("#fechaNac").val().length < 1)) {  
            alert("Todos los campos son obligatorios");  
            return false;  
        }

        var idP = $("#id-tomar").val();
        var nombreMascota = $("#nombre-mascota").val();
        var tipo = $("#tipo").val();
        var fechaNac = $("#fechaNac").val();

        $.ajax({
            url: '/mascota-propietario/'+idP,
            type: 'POST',        
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept","application/json");
            },
            contentType:'application/json',
            dataType:'json',
            data:JSON.stringify({"nombre":nombreMascota,"tipo":tipo,"fechaNac":fechaNac}),
                success : function(json){
                
                    swal("Listo!", "La mascota se ha guardado", "success");
                    $("#nombre-mascota").val('');
                    $("#tipo").val('');
                    $("#fechaNac").val('');

                    $.ajax({
                        url: '/mascota-propietario/'+idP,
                        type: 'GET',
                        dataType: 'json',
                        success : function(json){
                            $(".temporalM").remove();  
                            $.each(json, function( k, v ) {                
                                $("#listMascotas2").append(
                                                          
                                    '<tr class="temporalM">'+                            
                                        '<td>'+v.nombre+'</td>'+
                                        '<td>'+v.tipo.displayName+'</td>'+ 
                                        '<td>'+v.fechaNac+'</td>'+                                                                                            
                                    '</tr>'
                                )
                            });
                        },
                    });
                },           
        });

    });
}

