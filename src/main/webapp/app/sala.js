Vue.component('sala', {
	data: function () {
	    return {
	    	 input: {	    		 
                 sala: ""
             		},
	    	tipovi:[],
	    	id:null,
	    	izmena:"",
	    	salaPK:{
	    		brojSale:null,
	    		klinika:null
	    	}
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Sale</h2>
	  <p>Pretraga, dodavanje, izmena i brisanje.</p> 
	</div>
	<input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput" placeholder="Naziv sale">
	<input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/>
   <table align="left" class="table">
		<tr>
		   <th>Broj sale</th>
		   <th>Naziv</th>
		   <th>Izmena</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in tipovi" v-if="tp.klinika==salaPK.klinika" class="filterDiv " >
			<td class="myclass">{{tp.brojSale}}</td>
			<td class="myclass">{{tp.naziv}}</td>
			<td><input class="btn btn-warning btn-lg" value='Izmeni' type='button'  v-on:click="uredi(tp.brojSale,tp.naziv)"/></td>
			<td><input class="btn btn-danger btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.brojSale)"/></td>
		</tr>
		<tr>
			<td><input type="number" min="1" max="1000" class="fotrol" v-model="salaPK.brojSale" placeholder="Broj sale"></td>
			<td><input type="text" class="fotrol" v-model="input.sala" placeholder="Naziv sale"></td>
			<td><input class="btn btn-success" type='button' value='Dodavanje'  v-on:click="dodaj()"/></td>
		</tr>	
   </table>
   <div id="modaldark">
   <div class="form-popup" id="myForm">
    <h6>Broj sale:{{this.id}}</h6>
    <input type="text" class="psw" v-model="izmena" placeholder="Naziv sale">
    </br></br>
    <button type="button" class="btn maal leftbutton" v-on:click="izmeni()">Potvrdi</button>
    <button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		uredi(id,naziv) {
			this.id=id;
			this.izmena=naziv;
			document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
        },
		izmeni() {
        	if(this.izmena == "" || this.izmena.length>24){
        		alert("Popunite ispravno sva polja");
        	}else{
	        	axios
	        	.put('rest/sala/izmeni', {"brojSale":this.id,"klinika":this.salaPK.klinika, "naziv":this.izmena})
				.then(response =>{
	        		axios
	        	    .get('rest/sala')
	        	    .then(response => (this.tipovi=response.data));
	            });
				document.getElementById("myForm").style.display = "none";
				document.getElementById("modaldark").style.display = "none";
				document.getElementById("modaldark").style.opacity="0";
        	}

        },
        fjaPretrage() {
            var input, filter, ul, li, a, i, txtValue;
            input = document.getElementById('myInput');
            filter = input.value.toUpperCase();
            li = document.getElementsByClassName("filterDiv");

            for (i = 0; i < li.length; i++) {
              a = li[i].getElementsByTagName("td")[1];
              txtValue = a.textContent || a.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                li[i].style.display = "";
              } else {
                li[i].style.display = "none";
              }
           }
        },
		otkazi() {
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		obrisi(id) {
            axios
            .post("rest/sala/obrisi",{"brojSale":id,"klinika":this.salaPK.klinika})
            .then(response => {
        		axios
        	    .get('rest/sala')
        	    .then(response => (this.tipovi=response.data));
            })
            .catch(error => {
				alert("Sala je rezervisana");
			});
        },
		dodaj() {
        	if(this.proveraPolja()){
	        	axios
	        	.post('rest/sala/dodaj', {"brojSale":this.salaPK.brojSale,"klinika":this.salaPK.klinika, "naziv":this.input.sala})
				.then(response =>{
	        		axios
	        	    .get('rest/sala')
	        	    .then(response => (this.tipovi=response.data));
	            });
	        	this.salaPK.brojSale=null;
	        	this.input.sala="";
	    	}
	    	else{
	    		alert("Popunite ispravno sva polja");
	    	}
        },
        proveraPolja(){ 
        	for (var i of this.tipovi) {
        		if(i.brojSale==this.salaPK.brojSale && i.klinika==this.salaPK.klinika){
        			alert("Ovaj broj sale je zauzet");
        			return false;
        		}
			}
        	if(this.input.sala == "" || this.input.sala.length>24){
        		return false;
        	}
        	if(this.salaPK.brojSale == "" || this.salaPK.brojSale==null || this.salaPK.brojSale<1 || this.salaPK.brojSale>1000){
        		return false;
        	}
        	return true;     	
        },
        proveraIzmena(){
        	if(this.izmena == "" || this.izmena.length>24){
        		return false;
        	}
        }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
		    console.log(response.data);	
		})
		.catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/sala')
	    .then(response => (this.tipovi=response.data));
		axios
	    .get('rest/login/getKlinika')
	    .then(response =>(this.salaPK.klinika=response.data.id));
	}
});