Vue.component('zahtevi', {
	data: function(){
		return {
			zahtevi: [],
			id: null
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
</div>
</div>
	`
	,
	methods: {
		prihvati(id){
			axios
			.put('rest/pacijent/prihvati/'+id)
			.then(response => {
				axios
				.get('rest/pacijent/zahtevi')
				.then(response => (this.zahtevi=response.data));
			})
		},
		odbij(id){
			axios
			.delete('rest/pacijent/odbij/'+id)
			.then(response => {
				axios
				.get('rest/pacijent/zahtevi')
				.then(response => (this.zahtevi=response.data));
			})
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