<#import "../macros/common.ftlh" as c/>

<@c.page title="Owner">

	<h2>
        <#if owner?? && owner.new>
			New
            <#assign action = "new">
            <#assign buttonTitle = "Add Owner">
        <#else >
            <#assign action = "edit"> <#assign buttonTitle = "Update Owner">
        </#if> Owner

	</h2>
	<form action="${action}" method="post" class="form-horizontal" id="add-owner-form">
		<div class="form-group has-feedback">
			<label for="firstName">First Name</label>
			<input id="firstName" type="text" name="firstName" value="${owner.firstName!}">
			<br/>
			<label for="lastName">Last Name</label>
			<input id="lastName" type="text" name="lastName" value="${owner.lastName!}">
			<br/>
			<label for="address">Address</label>
			<input id="address" name="address" value="${owner.address!}">
			<br/>
			<label for="city">City</label>
			<input id="city" name="city" value="${owner.city!}">
			<br/>
			<label for="telephone">Telephone</label>
			<input id="telephone" name="telephone" value="${owner.telephone!}">
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">${buttonTitle}</button>
			</div>
		</div>
	</form>

</@c.page>
