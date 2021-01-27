-- 删除部署
UPDATE deployment SET  deleted=TRUE  WHERE tenant_id='${tenantId}';
-- 删除资源
UPDATE  resource  SET  deleted=TRUE WHERE tenant_id='${tenantId}';
-- 计费
UPDATE cmp_billings.bill_resource_schedule set deleted=true WHERE id='00283867-48fe-42ef-985b-51a29b5036ef';
