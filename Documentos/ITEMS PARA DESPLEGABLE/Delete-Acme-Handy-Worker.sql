start transaction;

use `Acme-Handy-Worker`;


drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Handy-Worker`;
commit;