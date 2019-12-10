<#import "../macros/common.ftlh" as c/>

<@c.page title="Owner Details">


	<h2>Owner Information</h2>


	<table class="table table-striped">
		<tr>
			<th>Name</th>
			<td><b>${owner.firstName} ${owner.lastName}</b></td>
		</tr>
		<tr>
			<th>Address</th>
			<td>${owner.address}</td>
		</tr>
		<tr>
			<th>City</th>
			<td>${owner.city}</td>
		</tr>
		<tr>
			<th>Telephone</th>
			<td>${owner.telephone}</td>
		</tr>
	</table>

	<a href="${owner.id}/edit" class="btn btn-default">Edit Owner</a>

	<a href="${owner.id}/pets/new" class="btn btn-default">Add New Pet</a>

	<br/>
	<br/>
	<br/>
	<h2>Pets and Visits</h2>

	<table class="table table-striped">
		<thead>
		<tr>
			<td>Name</td>
			<td>Birth Date</td>
			<td>Type</td>
			<td></td>
		</tr>
		</thead>
		<tbody>
        <#list owner.pets as pet>
			<tr>
				<td>${pet.name}</td>
				<td>${pet.birthDate}</td>
				<td>${pet.type.name}</td>
				<td>
                    <#if pet.visits?has_content >
						<table class="table-condensed">
							<thead>
							<tr>
								<td>Visit Date</td>
								<td>Description</td>
							</tr>
							</thead>
							<tbody>
                            <#list pet.visits as visit>
								<tr>
									<td>${visit.date}</td>
									<td>${visit.description}</td>
								</tr>
                            </#list>
							</tbody>
						</table>
                    </#if>
			</tr>
			<tr>
				<td>
					<a href="/owners/${pet.owner.id}/pets/${pet.id}/edit">Edit Pet</a>
				</td>
				<td>
					<a href="/owners/${pet.owner.id}/pets/${pet.id}/visits/new">Add Visit</a>
				</td>
			</tr>
        </#list>
		</tbody>
	</table>
</@c.page>