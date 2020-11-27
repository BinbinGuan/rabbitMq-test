-- 删除部署
UPDATE deployment SET  deleted=TRUE  WHERE tenant_id='default';

/*

asdfasdf
asdf
asdf
asdf
*/
UPDATE  resource  SET  deleted=TRUE WHERE tenant_id='default' ;