import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

/**
 * Created by lyndonxiang on 7/1/15.
 */
public class stanfordCoreNLP {

    public static void main(String[] args)
    {

        // read some text in the text variable
        String text = "John read a book yesterday. He found it amusing."; //muc6SplitDocs.readDoc("/Users/lyndonxiang/Desktop/muc6_wsj_withHeadline/891101-0050.txt");
//        String textWithTags = muc6SplitDocs.readDoc("/Users/lyndonxiang/Desktop/dryrun-trng.CO.key.v2.0.clean/891101-0050.co.v0.sgm");

        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        // these are all the sentences in this document
        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);


        int sentenceId = 0;
        for(CoreMap sentence: sentences) {
            int wordId = 0;

//            System.out.println(sentence.get(CoreAnnotations.TokensAnnotation.class).getClass());

            sentence.get(CoreAnnotations.TokensAnnotation.class).getClass();

//            List<CoreLabel> l = sentence.get(CoreAnnotations.TokensAnnotation.class);
//            System.out.println(l.get(5));

            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {

//                System.out.println(token.index());
//                System.out.println(token.value());
//                System.out.println(token.originalText());

                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);

                System.out.printf("%5d %5d %25s\n", sentenceId, wordId++, word);
            }
            sentenceId ++;
            //System.exit(0);
        }

    }

}
