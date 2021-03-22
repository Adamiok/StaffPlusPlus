package net.shortninja.staffplus.core.domain.staff.warn.appeals.database;

import net.shortninja.staffplus.core.application.IocBean;
import net.shortninja.staffplus.core.application.database.migrations.SqlConnectionProvider;
import net.shortninja.staffplus.core.domain.player.PlayerManager;

@IocBean(conditionalOnProperty = "storage.type=mysql")
public class MysqlAppealRepository extends AbstractSqlAppealRepository {

    public MysqlAppealRepository(PlayerManager playerManager, SqlConnectionProvider sqlConnectionProvider) {
        super(playerManager, sqlConnectionProvider);
    }
}
