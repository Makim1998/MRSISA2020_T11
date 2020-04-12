Vue.component("sala", {
	data: function () {
	    return {
	    	 input: {	    		 
                 sala: ""
             		},
	    	tipovi:[],
	    	id:null,
	    	izmena:""
	    }
	},
	template: ` 
<div>
<div>
	<div class="jumbotron">
	  <h2>Sale</h2>
	  <p>Pretraga, dodavanje, izmena i brisanje.</p> 
	</div>
   <table align="left" class="table">
		<tr>
		   <th>ID</th>
		   <th>Sala</th>
		   <th>Izmena</th>
		   <th>Brisanje</th>
		</tr>
		<tr v-for="tp in tipovi" >
			<td class="myclass">{{tp.id}}</td>
			<td class="myclass">{{tp.naziv}}</option>
			<td><input class="btn btn-warning btn-lg" value='Izmeni' type='button'  v-on:click="uredi(tp.id)"/></td>
			<td><input class="btn btn-danger btn-lg" value='Obrisi' type='button' v-on:click="obrisi(tp.id)"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" class="fotrol" v-model="input.sala" placeholder="Naziv sale"></td>
			<td><input class="btn btn-success" type='button' value='Dodavanje'  v-on:click="dodaj()"/></td>
			<td><router-link :to="{ name: 'administratorKlinike' }" tag="button" float='right' class="btn btn-primary" >Nazad</router-link></td>
		</tr>	
   </table>
   <div class="form-popup" id="myForm">
    <h6>Izmena ID:{{this.id}}</h6>
    <td><input type="text" class="psw" v-model="izmena" placeholder="Naziv sale"></td>
    <button type="button" class="btn maal" v-on:click="izmeni()">Potvrdi</button>
    <button type="button" class="btn zaal" v-on:click="otkazi()">Otkazi</button>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		uredi(id) {
			this.id=id;
			document.getElementById("myForm").style.display = "block";
        },
		izmeni() {
			alert("izmeni")       
        	axios
        	.put('rest/sala/izmeni', {"id":this.id, "naziv":this.izmena})
			.then(response => this.$router.replace({ name: "administratorKlinike" }));
			document.getElementById("myForm").style.display = "none";

        },
		otkazi() {
			document.getElementById("myForm").style.display = "none";
        },
		obrisi(id) {
            axios
            .delete("rest/sala/"+id,id)
            .then(response => this.$router.replace({ name: "administratorKlinike" }));
        },
		dodaj() {
        	axios
        	.post('rest/sala/dodaj', {"id": null, "naziv":this.input.sala})
			.then(response => this.$router.replace({ name: "administratorKlinike" }));
        }
	},
	mounted(){
		axios
	    .get('rest/sala')
	    .then(response => (this.tipovi=response.data));
	},
});