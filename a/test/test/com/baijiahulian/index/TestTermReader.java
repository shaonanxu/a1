package test.com.baijiahulian.index;

import java.io.File;
import java.util.List;

import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class TestTermReader {
	
	
	static void test() throws Exception{
		String f = "/Users/xushaonan/Workspace/index";
		
		IndexReader ir = DirectoryReader.open(FSDirectory.open(new File(f)));
		
		List<AtomicReaderContext> list = ir.leaves();
		System.out.println(list.size());
		
		for(AtomicReaderContext arc : list){
			
		}
		
//		IndexSearcher is = new IndexSearcher(ir);
//		
//		
//		TopDocs tDocs = is.search(new TermQuery(new Term("skill", "张老师")), 100);
//		for(ScoreDoc doc : tDocs.scoreDocs){
//			System.out.println(doc.doc);
//		}
		ir.close();
	}
	
	
	public static void main(String[] args) throws Exception{
		test();
	}

}
