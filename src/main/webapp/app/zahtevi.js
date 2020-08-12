Vue.component('zahtevi', {
	data: function(){
		return {
			zahtevi: [],
			id: null,
			razlog: "",
		}
	},
	template: `
<div class="oneoption">
<div>
<h2 class="text-center">Zahtevi za registracije</h2>
<br>
   <table align="left" class="table klasicna-tabela">
		<tr>
		   <th>Id</th>
		   <th>Email</th>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Prihvatanje</th>
		   <th>Odbijanje</th>
		</tr>
		<tr v-for="z in zahtevi" class="filterDiv " >
			<td class="myclass">{{z.id}}</td>
			<td class="myclass">{{z.email}}</td>
			<td class="myclass">{{z.ime}}</td>
			<td class="myclass">{{z.prezime}}</td>
			<td><input class="btn btn-primary btn-lg" value='Prihvati' type='button'  v-on:click="prihvati(z.id)"/></td>
			<td><input class="btn btn-primary btn-lg" value='Odbij' type='button' v-on:click="odbij(z.id)"/></td>
		</tr>	
   </table>
   <div id="modaldark">
		<div class="form-popup" id="myForm">
			<h6>Navedite razlog za odbijanje zahteva:</h6>
			<textarea style="width:250px;height:150px;" class="psw" v-model="razlog"></textarea>
			</br></br>
			<button type="button" class="btn maal leftbutton" v-on:click="potvrda()">Potvrdi</button>
			<button type="button" class="btn zaal rightbutton" v-on:click="otkazi()">Otkazi</button>
		</div>
	</div>
</div>
</div>
	`
	,
	methods: {
		prihvati(id){
			console.log("Stiglo je do frontend-a za prihvatanje");
			axios
			.put('rest/pacijent/prihvati/'+id)
			.then(response => {
				axios
				.get('rest/pacijent/zahtevi')
				.then(response => (this.zahtevi=response.data));
			});
		},
		odbij(id){
			console.log("Stiglo je do frontend-a za odbijanje");
			this.id = id;
        	document.getElementById("myForm").style.display = "block";
			document.getElementById("modaldark").style.display = "block";
			document.getElementById("modaldark").style.opacity="1";
		},
		potvrda(){
        	if (this.razlog.trim() == "")
        		alert("Niste uneli razlog za odbijanje zahteva!");
        	else{
        		axios
    			.delete('rest/pacijent/odbij/'+this.id+"/"+this.razlog)
    			.then(response => {
    				axios
    				.get('rest/pacijent/zahtevi')
    				.then(response => (this.zahtevi=response.data));
    			});
        		this.otkazi();
        	}
        },
        otkazi(){
        	document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        }
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/AdminKC')
	    .then((response) => {
		    console.log(response.data);	
		})
		.catch(response => {
			this.$router.push("/");
		});
		axios
		.get('rest/pacijent/zahtevi')
		.then(response => (this.zahtevi=response.data));
	}
})