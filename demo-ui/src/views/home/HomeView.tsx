import React from "react";
import {IProps} from "./IProps";
import "./HomeView.scss"
import {classNameBuilder} from "../../utils/className";

const HomeView: React.FC<IProps> = () => {
  const cn = classNameBuilder("home")
  return (
    <div className={cn()}>
      <h2>home view page</h2>
    </div>
  )
}

export default HomeView;