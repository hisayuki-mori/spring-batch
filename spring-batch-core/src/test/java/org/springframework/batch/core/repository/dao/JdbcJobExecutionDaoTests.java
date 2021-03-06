package org.springframework.batch.core.repository.dao;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "sql-dao-test.xml" })
public class JdbcJobExecutionDaoTests extends AbstractJobExecutionDaoTests {

	@Autowired
	private StepExecutionDao stepExecutionDao;

	@Autowired
	private JobExecutionDao jobExecutionDao;

	@Autowired
	private JobInstanceDao jobInstanceDao;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	protected JobInstanceDao getJobInstanceDao() {
		return jobInstanceDao;
	}

	@Override
	protected JobExecutionDao getJobExecutionDao() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "BATCH_JOB_EXECUTION_CONTEXT",
				"BATCH_STEP_EXECUTION_CONTEXT", "BATCH_STEP_EXECUTION", "BATCH_JOB_EXECUTION", "BATCH_JOB_EXECUTION_PARAMS",
				"BATCH_JOB_INSTANCE");
		return jobExecutionDao;
	}

	@Override
	protected StepExecutionDao getStepExecutionDao() {
		return stepExecutionDao;
	}

}
