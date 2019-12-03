<#import "../macros/common.ftlh" as c/>

<@c.page title="Owner">


<h2>Owners</h2>

<table id="ownersTable" class="table table-striped">
	<thead>
	<tr>
		<th style="width: 150px;">Name</th>
		<th style="width: 200px;">Address</th>
		<th>City</th>
		<th style="width: 120px">Telephone</th>
		<th>Pets</th>
	</tr>
	</thead>
	<tbody>
    <#if owners??>
        <#list owners as owner>
			<tr>
				<td>
                    <#assign ownerUrl ="/owners/${owner.id}">
					<a href="${ownerUrl}">
                        ${owner.firstName} ${owner.lastName}
					</a>
				</td>
				<td>
                    ${owner.address}
				</td>
				<td>
                    ${owner.city}
				</td>
				<td>
                    ${owner.telephone}
				</td>
				<td>
                    <#list owner.pets as pet>
                        ${pet.name}
                    </#list>
				</td>
			</tr>
        </#list>
    </#if>
	</tbody>
</table>

</@c.page>