Vue.component("pacijenti", {
	data: function () {
	    return {
	    	 input: {	    		 
                 ime: "",
                 prezime: "",
                 brojOsiguranika: ""
             		},
	    	tipovi:[],
	    	id:null,
	    	izmena:"",
	    	klinika_id:null
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron">
	  <h2>Pacijenti</h2>
	  <p>Pretraga i filtriranje.</p> 
	</div>
   <table align="left" class="table">
   		<tr>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput1" placeholder="Ime"></th>
		   <input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage(0)"/>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput2" placeholder="Ime"></th>
		   <input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage(2)"/>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput3" placeholder="Ime"></th>
		   <input class="btn btn-success" type='button' value='Pretrazi'  v-on:click="fjaPretrage(4)"/>
		</tr>
		<tr>
		   <th>Ime</th>
		   <th></th>
		   <th>Prezime</th>
		   <th></th>
		   <th>Broj osiguranika</th>
		   <th></th>
		   <th>Profil</th>
		</tr>
		<tr v-for="tp in tipovi" v-if="tp.kc_id==klinika_id" class="filterDiv ">
			<td class="myclass">{{tp.ime}}</td>
			<td></td>
			<td class="myclass">{{tp.prezime}}</td>
			<td></td>
			<td class="myclass">{{tp.brojOsiguranika}}</td>
			<td></td>
			<td><input class="btn btn-danger btn-lg" value='Profil' type='button' v-on:click="pregled(tp.brojOsiguranika)"/></td>
		</tr>
   </table>
</div>
</div>		  
`
	, 
	methods : {
		obrisi(id) {
            axios
            .delete("rest/lekari/"+id,id)
            .then(response =>{		
				axios
			    .get('rest/lekari')
			    .then(response => (this.tipovi=response.data));
			})
			.catch(error => {
				alert("Lekar se ne moze obrisati,ima zakazan pregled.");
			});
        },
        fjaPretrage(n) {
          var input, filter, ul, li, a, i, txtValue,n;
          if(n==0){
        	  input = document.getElementById('myInput1');
          }else if(n==2){
        	  input = document.getElementById('myInput2');
          }else{
        	  input = document.getElementById('myInput3');
          }
          filter = input.value.toUpperCase();
          li = document.getElementsByClassName("filterDiv");

          for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("td")[n];
            txtValue = a.textContent || a.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
              li[i].style.display = "";
            } else {
              li[i].style.display = "none";
            }
          }
        }
	},
	mounted(){
		axios
		.get('rest/login/getConcreteUser/Lekar')
	    .then((response) => {
	    	this.klinika_id=response.data.kc_id;
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/pacijent/svi')
	    .then(response => (this.tipovi=response.data));

	},
});