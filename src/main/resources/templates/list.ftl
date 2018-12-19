<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Cliente </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.client.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Nome do Cliente
	                        <span class"required">*<span/>
	                        </label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.client.name" id="uname" class="username form-control input-sm" placeholder="Entre com o Nome do Cliente" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="creditLimit">Limite de Crédito
	                        	<span class"required">*<span/>
	                        </label>
	                        <div class="col-md-7">
	                            <input type="text" required ng-pattern="ctrl.onlyNumbers" ng-model="ctrl.client.creditLimit" id="creditLimit" class="form-control input-sm" placeholder="Entre com o Limite de credito." required ng-pattern="ctrl.onlyIntegers"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="risckType">Tipo de Risco
	                        	<span class"required">*<span/>
	                        </label>
	                        <div class="col-md-7">
	                        	<select required name="riscos" id="riscos" ng-model="ctrl.client.risckType" class="form-control">
							      <option value="">Selecione um Risco</option>
							      <option value="A">A</option>
							      <option value="B">B</option>
							      <option value="C">C</option>
							    </select>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <button  type="submit" id="salvar" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        	{{!ctrl.client.id ? 'Salvar' : 'Atualizar'}}
	                        	<i class="{{!ctrl.client.id ? 'icon-save' : 'icon-refresh'}}"></i>
	                        </button>
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-default btn-sm" ng-disabled="myForm.$pristine">
	                        	Limpar
	                        	<i class="icon-eraser"></i>
	                        </button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Clientes </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>NOME</th>
		                <th>LIMITE DE CRÉDITO</th>
		                <th>TIPO DE RISCO</th>
		                <th>%</th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="client in ctrl.getAllClients()">
		                <td>{{client.name}}</td>
		                <td>{{client.creditLimit}}</td>
		                <td>{{client.risckType}}</td>
		                <td>{{client.percentRisk}}</td>
		                <td>
		                	<button type="button" ng-click="ctrl.editClient(client.id)" class="btn" title="Editar">
		                		<i class="icon-pencil"></i>
		                	</button>
		                	<button tpe="button" ng-click="ctrl.removeClient(client.id)" class="btn" title="Remove">
		                		<i class="icon-trash"></i>
		                	</button>
		                </td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>