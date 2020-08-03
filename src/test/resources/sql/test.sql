SELECT bill.customer_name AS userName, JSON_UNQUOTE(JSON_EXTRACT(user.exts, '$.zain.typeCompany')) AS idType
	, JSON_UNQUOTE(JSON_EXTRACT(user.exts, '$.zain.idNumber')) AS idNumber
	, JSON_UNQUOTE(JSON_EXTRACT(user.exts, '$.zain.msisdn')) AS msisdn
	, JSON_UNQUOTE(JSON_EXTRACT(deployment.exts, '$.poNumber')) AS poNumber
	, catalog.name AS resourceType, detail.deployment_id AS requestNo, task.operation_name AS service, detail.pay_type AS billMethod
	, FROM_UNIXTIME(detail.start_time / 1000, '%Y/%m/%d %H:%i') AS startTime
	, FROM_UNIXTIME(detail.end_time / 1000, '%Y/%m/%d %H:%i') AS expiryTime
	, detail.amount AS amount
FROM account_bill_detail detail
	LEFT JOIN account_bill bill ON detail.account_bill_id = bill.id
	LEFT JOIN user ON user.id = bill.customer_id
	LEFT JOIN deployment ON detail.deployment_id = deployment.id
	LEFT JOIN catalog ON deployment.catalog_id = catalog.id
	LEFT JOIN generic_task task ON detail.generic_task_id = task.id
WHERE (detail.start_time >= 1582992000000
	AND detail.start_time <= 1585104044000
	AND (JSON_EXTRACT(user.exts, '$.zain.customerType') = 'saas_provider'
		OR JSON_EXTRACT(user.exts, '$.zain.customerType') = 'company_customer'))
ORDER BY bill.customer_name;