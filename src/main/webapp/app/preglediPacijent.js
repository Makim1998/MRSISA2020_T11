Vue.component('pregledi',{
	data:function(){
		return{
			pregledi:[],
			email: ""
		}
	},
	template: ` 
<div id = "preglediPacijent">
<h2 class="text-center">Pregledi</h2>
<button  type="button" class="btn btn-primary"  v-on:click="skok()" >Zakazi novi</button>
<br>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Datum</th>
      <th scope="col">Tip pregleda</th>
      <th scope="col">Klinika</th>
      <th scope="col">Lekar</th>
      <th scope="col">Cena</th>
      <th scope="col">Detalji</th>
    </tr>
  </thead>
  <tbody>
		<tr v-for="p in pregledi">
			<td >{{p.formatiran}}</td>
			<td >{{p.tip.naziv}}</td>
			<td >{{p.lekar.klinika}}</td>
			<td >{{p.lekar.ime}} {{p.lekar.prezime}}</td>
			<td>{{p.cena.cena}}</td>
			<td>
			<button type="button"  class="btn btn-primary" data-toggle="modal" data-target="#detaljiModal">
				Detalji
			</button>
			</td>
		</tr>
  </tbody>
</table>

<h5 class="text-center" id = "rezultatiPretrage"></h5>
</div>		  		  
`
	, 
	methods : {
		skok(){
			console.log("novi");
        	this.$emit('novi');
		}
		
	},
	mounted(){
		axios
	    .get('rest/login/getConcreteUser/Pacijent')
	    .then((response) => {
	    	console.log(response.data);
	    	this.email = response.data.email;
	    	console.log(this.email);
	    	axios
		    .get('rest/Pregled/zaPacijenta?email='+ this.email)
		    .then((response) => {
		    	this.pregledi = response.data;
		    	console.log(response.data);
		    	if(this.pregledi.length == 0){
					console.log("nema nadjenih");
					$("#rezultatiPretrage").html("Nema pregleda");
				}
		    })
	    	 .catch(function(error){
 				if(error.response){
 					alert("Greska.");
 				};
	    	 });
	    })
	    .catch(response => {
	    	this.$router.push("/");
		});	
		
	}
});