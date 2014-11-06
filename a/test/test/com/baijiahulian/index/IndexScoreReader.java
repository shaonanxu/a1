package test.com.baijiahulian.index;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class IndexScoreReader {
	
	public static void test() throws Exception{
		
		String f = "/Users/xushaonan/Workspace/index";
		String q = 	"+((+(realname:张老师 nickname:张老师)) (+(uid:张老师 user_number:张老师 sub_name:张老师 area_name:张老师 "
				+ "introduce:张老师 case:张老师 content:张老师 skill:张老师 organization:张老师))) +area_id:17039360";

		BooleanQuery query = new BooleanQuery();
		
		BooleanQuery q11 = new BooleanQuery();
		BooleanQuery q1 = new BooleanQuery();
		q1.add(new TermQuery(new Term("realname", "张老师")), Occur.SHOULD);
		q1.add(new TermQuery(new Term("nickname", "张老师")), Occur.SHOULD);
		q11.add(q1, Occur.MUST);
		
		BooleanQuery q2 = new BooleanQuery();
		q2.add(new TermQuery(new Term("uid", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("user_number", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("sub_name", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("area_name", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("introduce", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("case", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("content", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("skill", "张老师")), Occur.SHOULD);
		q2.add(new TermQuery(new Term("organization", "张老师")), Occur.SHOULD);
		
		BooleanQuery q22 = new BooleanQuery();
		q22.add(q2, Occur.MUST);
		
		query.add(q11, Occur.SHOULD);
		query.add(q22, Occur.SHOULD);
		
		BooleanQuery q3 = new BooleanQuery();
		q3.add(query, Occur.MUST);
		q3.add(new TermQuery(new Term("area_id", "17039360")), Occur.MUST);
	    	
		System.out.println(q3);
		
		IndexReader ir = DirectoryReader.open(FSDirectory.open(new File(f)));
		IndexSearcher is = new IndexSearcher(ir);
		
		TopDocs tDocs = is.search(query, 100);
		int[] docs = new int[10];
		int i = 0;
		Set<String> uns = new HashSet<>();
		Document d = null;
		for(ScoreDoc sd : tDocs.scoreDocs){
			d = is.doc(sd.doc);
			String un = d.getField("user_number").stringValue();
			System.out.println(sd.doc);
			if(uns.contains(un))
				continue;
//			System.out.println(d.getField("skill").stringValue());
			System.out.println(un);
			uns.add(un);
			docs[i++] = sd.doc;
			if(i == 10) break;
//			System.out.println(d.getField("introduce").stringValue());
		}
		outputDoc(d);
		setOut();
		for(int doc : docs){
			System.out.println(is.explain(query, doc));
		}
	}
	
	private static void outputDoc(Document doc) {
		for(IndexableField f : doc.getFields()){
			System.out.println(f.name());
		}
	}

	static void setOut() throws FileNotFoundException{
		System.setOut(new PrintStream(new File("/Users/xushaonan/Workspace/logs/score.log")));
	}
	
	public static void main(String[] args) throws Exception {
		test();
	}

}
