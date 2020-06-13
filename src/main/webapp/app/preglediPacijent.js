Vue.component('pregledi',{
	data:function(){
		return{
			pregledi:[],
			email: "",
			dijagnoza: ""
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
			<button type="button"  v-on:click = "dijagnoza = p.opis" class="btn btn-primary" data-toggle="modal" data-target="#detaljiModal">
				Detalji
			</button>
			</td>
		</tr>
  </tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="detaljiModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Dijagnoza pregleda: </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<p>{{dijagnoza}}</p>
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Zatvori</button>
      </div>
    </div>
  </div>
</div>

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