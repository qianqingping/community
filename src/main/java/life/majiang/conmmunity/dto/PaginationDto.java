package life.majiang.conmmunity.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer cruuentPage;
    private List<Integer> pages = new ArrayList<>();

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getCruuentPage() {
        return cruuentPage;
    }

    public void setCruuentPage(Integer cruuentPage) {
        this.cruuentPage = cruuentPage;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public void setPageination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        if(totalCount%size == 0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }
        pages.add(page);
        for ( int i=0;i<=3;i++){
            if (page-i>0){
                pages.add(page-i,0);
            }
            if (page+i<=totalCount){
                pages.add(page+1);
            }
        }

        if (page==1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        if (page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }
        if (pages.contains(1)){

            showFirstPage= false;

        }else {
            showFirstPage = true;
        }if (pages.contains(totalPage)){

            showEndPage= false;

        }else {
            showEndPage = true;
        }
    }
}