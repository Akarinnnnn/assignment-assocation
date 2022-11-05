package oop.clubsv3.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;

@Component
@Scope("singleton")
public class DbConnectionBean
{
	private final SqlSessionFactory fac;
	
	DbConnectionBean() throws IOException
	{
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//noinspection SpellCheckingInspection
		try(Reader cfg = Resources.getResourceAsReader("mybatisctx.xml"))
		{
			fac = builder.build(cfg);
		}
	}
	
	public SqlSession getConnection()
	{
		return fac.openSession();
	}
}
